//package declaration
package ch.nolix.coreTest.webSocketTest;

//own import
import ch.nolix.core.baseTest.TestPool;

//class
public final class WebSocketTestPool extends TestPool {
	
	//constructor
	public WebSocketTestPool() {
		addTestClass(WebSocketHandShakeResponseTest.class);
	}
}
