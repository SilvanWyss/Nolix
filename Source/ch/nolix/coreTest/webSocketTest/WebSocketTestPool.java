//package declaration
package ch.nolix.coreTest.webSocketTest;

import ch.nolix.common.baseTest.TestPool;

//class
public final class WebSocketTestPool extends TestPool {
	
	//constructor
	public WebSocketTestPool() {
		addTestClass(WebSocketHandShakeResponseTest.class);
	}
}
