//package declaration
package ch.nolix.commontest.websockettest;

import ch.nolix.common.basetest.TestPool;

//class
public final class WebSocketTestPool extends TestPool {
	
	//constructor
	public WebSocketTestPool() {
		super(
			WebSocketFramePayloadLengthTest.class,
			WebSocketFrameTest.class,
			WebSocketHandShakeRequestTest.class,
			WebSocketHandShakeResponseTest.class
		);
	}
}
