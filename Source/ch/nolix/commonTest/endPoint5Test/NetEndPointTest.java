//package declaration
package ch.nolix.commonTest.endPoint5Test;

//own imports
import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.common.controllerAPI.IDataProviderController;
import ch.nolix.common.endPoint5.EndPoint;
import ch.nolix.common.endPoint5.IEndPointTaker;
import ch.nolix.common.endPoint5.NetEndPoint;
import ch.nolix.common.endPoint5.NetServer;
import ch.nolix.common.node.Node;
import ch.nolix.common.test.Test;

//test class
/**
 * A {@link NetEndPointTest} is a test for {@link NetEndPoint}.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 140
 */
public final class NetEndPointTest extends Test {
	
	//mock class
	private static class EndPointTakerMock implements IEndPointTaker {
		
		//optional attributes
		private ChainedNode command;
		private ChainedNode request;
		
		//method
		@Override
		public String getName() {
			return "EndPointTaker";
		}
		
		//method
		public ChainedNode getReceivedCommandOrNull() {
			return command;
		}
		
		//method
		public ChainedNode getReceivedRequestOrNull() {
			return request;
		}
		
		//method
		@Override
		public void takeEndPoint(final EndPoint endPoint) {
			endPoint.setReceiverController(new IDataProviderController() {
				
				@Override
				public Node getData(final ChainedNode request) {
					EndPointTakerMock.this.setRequest(request);
					return new Node("DATA");
				}
				
				@Override
				public void run(final ChainedNode command) {
					EndPointTakerMock.this.setCommand(command);
					
				}
			});
		}
		
		//method
		private void setCommand(final ChainedNode command) {
			this.command = command;
		}
		
		//method
		private void setRequest(final ChainedNode request) {
			this.request = request;
		}
	}
	
	//test case
	public void testCase_creation() {
		
		//test parameter
		final var port = 50000;
		
		//setup
		final var netServer = new NetServer(port);
		netServer.addMainEndPointTaker(new EndPointTakerMock());
		
		//execution & verification
		expect(() -> new NetEndPoint(port).close()).doesNotThrowException();
		
		//cleanup
		netServer.close();
	}
	
	//test case
	public void testCase_run() throws InterruptedException {
		
		//test parameter
		final var port = 50000;
		
		//setup
		final var netServer = new NetServer(port);
		final var endPointTakerMock = new EndPointTakerMock();
		netServer.addMainEndPointTaker(endPointTakerMock);
		final var netEndPoint = new NetEndPoint(port);
		
		//execution
		netEndPoint.run(new ChainedNode("COMMAND"));
		
		//verification
		expect(endPointTakerMock.getReceivedCommandOrNull()).isEqualTo(new ChainedNode("COMMAND"));
		
		//cleanup
		netEndPoint.close();
		netServer.close();
	}
	
	//test case
	public void testCase_getData() throws InterruptedException {
		
		//test parameter
		final var port = 50000;
		
		//setup
		final var netServer = new NetServer(port);
		final var endPointTakerMock = new EndPointTakerMock();
		netServer.addMainEndPointTaker(endPointTakerMock);
		final var netEndPoint = new NetEndPoint(port);
		
		//execution
		final var data = netEndPoint.getData(new ChainedNode("REQUEST"));
		
		//verification
		expect(endPointTakerMock.getReceivedRequestOrNull()).isEqualTo(new ChainedNode("REQUEST"));
		expect(data).isEqualTo(new Node("DATA"));
		
		//cleanup
		netEndPoint.close();
		netServer.close();
	}
}
