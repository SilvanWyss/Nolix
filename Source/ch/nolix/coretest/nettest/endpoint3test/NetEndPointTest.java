//package declaration
package ch.nolix.coretest.nettest.endpoint3test;

//own imports
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.net.endpoint3.EndPoint;
import ch.nolix.core.net.endpoint3.IEndPointTaker;
import ch.nolix.core.net.endpoint3.NetEndPoint;
import ch.nolix.core.net.endpoint3.Server;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.chainednodeapi.IChainedNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.netapi.endpoint3api.IDataProviderController;

//class
public final class NetEndPointTest extends Test {
	
	//static class
	private static final class EndPointTaker implements IEndPointTaker {
		
		//optional attribute
		private IChainedNode command;
		
		//optional attribute
		private IChainedNode request;
		
		//method
		@Override
		public String getName() {
			return "EndPointTaker";
		}
		
		//method
		public IChainedNode getReceivedCommandOrNull() {
			return command;
		}
		
		//method
		public IChainedNode getReceivedRequestOrNull() {
			return request;
		}
		
		//method
		@Override
		public void takeEndPoint(final EndPoint endPoint) {
			endPoint.setReceivingDataProviderController(
				new IDataProviderController() {
					
					//method
					@Override
					public Node getDataForRequest(final IChainedNode request) {
						EndPointTaker.this.setRequest(request);
						return Node.withHeader("DATA");
					}
					
					//method
					@Override
					public IContainer<INode<?>> getDataForRequests(final IChainedNode request, final IChainedNode... requests) {
						//TODO: Implement.
						return null;
					}
					
					//method
					@Override
					public IContainer<INode<?>> getDataForRequests(final Iterable<? extends IChainedNode> requests) {
						//TODO: Implement.
						return null;
					}
					
					//method
					@Override
					public void runCommand(final IChainedNode command) {
						EndPointTaker.this.setCommand(command);
					}
					
					//method
					@Override
					public final void runCommands(final IChainedNode command, final IChainedNode... commands) {
						
						runCommand(command);
						
						for (final var c : commands) {
							runCommand(c);
						}
					}
					
					//method
					@Override
					public void runCommands(final Iterable<? extends IChainedNode> commands) {
						//TODO: Implement.
					}
				}
			);
		}
		
		//method
		private void setCommand(final IChainedNode command) {
			this.command = command;
		}
		
		//method
		private void setRequest(final IChainedNode request) {
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
			server.addDefaultSlot(new EndPointTaker());
			
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
			server.addDefaultSlot(endPointTaker);
			
			try (final var testUnit = new NetEndPoint(port)) {
			
				//execution
				testUnit.runCommand(ChainedNode.withHeader("COMMAND"));
				
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
			server.addDefaultSlot(endPointTaker);
			
			try (final var testUnit = new NetEndPoint(port)) {
				
				//execution
				final var result = testUnit.getDataForRequest(ChainedNode.withHeader("REQUEST"));
				
				//verification
				expect(endPointTaker.getReceivedRequestOrNull()).isEqualTo(ChainedNode.withHeader("REQUEST"));
				//TODO: expect(result).isEqualTo(Node.withHeader("DATA"));
			}
		}
	}
}
