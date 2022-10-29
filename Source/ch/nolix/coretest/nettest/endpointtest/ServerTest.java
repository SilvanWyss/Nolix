//package declaration
package ch.nolix.coretest.nettest.endpointtest;

//own imports
import ch.nolix.core.net.endpoint.Server;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class ServerTest extends Test {
	
	//method
	@TestCase
	public void testCase_close() {
		
		//parameter definition
		final var port = 50000;
		
		//setup
		try (final var server = new Server(port)) {
		
			//setup verification
			expect(server.getPort()).isEqualTo(port);
			expect(server.isOpen());
			
			//execution & verification
			expectRunning(server::close).doesNotThrowException();
			
			//verification
			expect(server.isClosed());
		}
	}
}
