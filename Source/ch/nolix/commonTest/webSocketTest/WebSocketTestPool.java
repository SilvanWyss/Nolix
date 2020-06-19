//package declaration
package ch.nolix.commonTest.webSocketTest;

//own import
import ch.nolix.common.baseTest.TestPool;

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
