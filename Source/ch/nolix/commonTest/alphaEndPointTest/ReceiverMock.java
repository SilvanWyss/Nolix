package ch.nolix.commonTest.alphaEndPointTest;

import ch.nolix.common.interfaces.IZetaReceiver;

public class ReceiverMock implements IZetaReceiver {

	@Override
	public String receiveMessageAndGetReply(String message) {
		return "ok";
	}

}
