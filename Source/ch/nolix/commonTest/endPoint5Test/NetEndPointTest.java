//package declaration
package ch.nolix.commonTest.endPoint5Test;

import ch.nolix.common.basetest.TestCase;
import ch.nolix.common.chainednode.ChainedNode;
import ch.nolix.common.controllerapi.IDataProviderController;
import ch.nolix.common.endpoint5.EndPoint;
import ch.nolix.common.endpoint5.IEndPointTaker;
import ch.nolix.common.endpoint5.NetEndPoint;
import ch.nolix.common.endpoint5.NetServer;
import ch.nolix.common.node.Node;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.test.Test;

//class
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
	
	//method
	@TestCase
	public void testCase_creation() {
		
		//test parameter
		final var port = 50000;
		
		//setup
		final var netServer = new NetServer(port);
		netServer.addMainEndPointTaker(new EndPointTakerMock());
		
		//execution & verification
		expect(
			() -> {
				final var netEndPoint = new NetEndPoint(port);
				Sequencer.waitForMilliseconds(500);
				netEndPoint.close();
			}
		)
		.doesNotThrowException();
		
		//cleanup
		netServer.close();
	}
	
	//method
	@TestCase
	public void testCase_run() {
		
		//test parameter
		final var port = 50000;
		
		//setup
		final var netServer = new NetServer(port);
		final var endPointTakerMock = new EndPointTakerMock();
		netServer.addMainEndPointTaker(endPointTakerMock);
		final var netEndPoint = new NetEndPoint(port);
		
		//execution
		netEndPoint.run(ChainedNode.withHeader("COMMAND"));
		
		//verification
		expect(endPointTakerMock.getReceivedCommandOrNull()).isEqualTo(ChainedNode.withHeader("COMMAND"));
		
		//cleanup
		netEndPoint.close();
		netServer.close();
	}
	
	//method
	@TestCase
	public void testCase_getData() {
		
		//test parameter
		final var port = 50000;
		
		//setup
		final var netServer = new NetServer(port);
		final var endPointTakerMock = new EndPointTakerMock();
		netServer.addMainEndPointTaker(endPointTakerMock);
		final var netEndPoint = new NetEndPoint(port);
		
		//execution
		final var data = netEndPoint.getData(ChainedNode.withHeader("REQUEST"));
		
		//verification
		expect(endPointTakerMock.getReceivedRequestOrNull()).isEqualTo(ChainedNode.withHeader("REQUEST"));
		expect(data).isEqualTo(new Node("DATA"));
		
		//cleanup
		netEndPoint.close();
		netServer.close();
	}
}
