//package declaration
package ch.nolix.commontest.nettest.endpoint3test;

import ch.nolix.common.controllerapi.IDataProviderController;
import ch.nolix.common.document.chainednode.ChainedNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.net.endpoint3.EndPoint;
import ch.nolix.common.net.endpoint3.IEndPointTaker;
import ch.nolix.common.net.endpoint3.NetEndPoint;
import ch.nolix.common.net.endpoint3.NetServer;
import ch.nolix.common.programcontrol.sequencer.Sequencer;
import ch.nolix.common.testing.basetest.TestCase;
import ch.nolix.common.testing.test.Test;

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
					return Node.withHeader("DATA");
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
		
		//parameter definition
		final var port = 50000;
		
		//setup
		final var netServer = new NetServer(port);
		netServer.addMainEndPointTaker(new EndPointTakerMock());
		
		//execution & verification
		expectRunning(
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
		
		//parameter definition
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
		
		//parameter definition
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
		expect(data).isEqualTo(Node.withHeader("DATA"));
		
		//cleanup
		netEndPoint.close();
		netServer.close();
	}
}
