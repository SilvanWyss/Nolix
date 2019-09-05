//package declaration
package ch.nolix.commonTest.webSocketTest;

import ch.nolix.common.baseTest.TestPool;

//class
public final class WebSocketTestPool extends TestPool {
	
	//constructor
	public WebSocketTestPool() {
		addTestClass(
			WebSocketHandShakeRequestTest.class,
			WebSocketHandShakeResponseTest.class
		);
	}
}
