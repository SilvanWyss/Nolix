//package declaration
package ch.nolix.coretest.nettest.endpoint3test;

//own imports
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.net.controlleruniversalapi.IDataProviderController;
import ch.nolix.core.net.endpoint3.EndPoint;
import ch.nolix.core.net.endpoint3.IEndPointTaker;
import ch.nolix.core.net.endpoint3.NetEndPoint;
import ch.nolix.core.net.endpoint3.Server;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class NetEndPointTest extends Test {
	
	//static class
	private static final class EndPointTaker implements IEndPointTaker {
		
		//optional attribute
		private ChainedNode command;
		
		//optional attribute
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
				
				//method
				@Override
				public Node getData(final ChainedNode request) {
					EndPointTaker.this.setRequest(request);
					return Node.withHeader("DATA");
				}
				
				//method
				@Override
				public void run(final ChainedNode command) {
					EndPointTaker.this.setCommand(command);
				}
				
				//method
				@Override
				public final void run(final ChainedNode... commands) {
					for (final var c : commands) {
						run(c);
					}
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
				
		try (final var server = new Server(port)) {
			
			//setup
			server.addDefaultEndPointTaker(new EndPointTaker());
			
			//execution & verification
			expectRunning(
				() -> {
					try (final var result = new NetEndPoint(port)) {
						GlobalSequencer.waitForMilliseconds(500);
					}
				}
			)
			.doesNotThrowException();
		}
	}
	
	//method
	@TestCase
	public void testCase_run() {
		
		//parameter definition
		final var port = 50000;
		
		try (final var server = new Server(port)) {
			
			//setup
			final var endPointTaker = new EndPointTaker();
			server.addDefaultEndPointTaker(endPointTaker);
			
			try (final var testUnit = new NetEndPoint(port)) {
			
				//execution
				testUnit.run(ChainedNode.withHeader("COMMAND"));
				
				//verification
				expect(endPointTaker.getReceivedCommandOrNull()).isEqualTo(ChainedNode.withHeader("COMMAND"));
			}
		}
	}
	
	//method
	@TestCase
	public void testCase_getData() {
		
		//parameter definition
		final var port = 50000;
				
		try (final var server = new Server(port)) {
			
			//setup
			final var endPointTaker = new EndPointTaker();
			server.addDefaultEndPointTaker(endPointTaker);
			
			try (final var testUnit = new NetEndPoint(port)) {
				
				//execution
				final var result = testUnit.getData(ChainedNode.withHeader("REQUEST"));
				
				//verification
				expect(endPointTaker.getReceivedRequestOrNull()).isEqualTo(ChainedNode.withHeader("REQUEST"));
				expect(result).isEqualTo(Node.withHeader("DATA"));
			}
		}
	}
}
