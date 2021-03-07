//package declaration
package ch.nolix.commontest.nettest.websockettest;

import ch.nolix.common.testing.basetest.TestPool;

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
