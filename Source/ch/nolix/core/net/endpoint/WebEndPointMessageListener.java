//package declaration
package ch.nolix.core.net.endpoint;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.websocket.WebSocketCompleteMessage;
import ch.nolix.core.programcontrol.worker.Worker;

//class
final class WebEndPointMessageListener extends Worker {
	
	//static method
	public static WebEndPointMessageListener forWebEndPoint(final WebEndPoint webEndPoint) {
		return new WebEndPointMessageListener(webEndPoint);
	}
	
	//attribute
	private final WebEndPoint parentWebEndPoint;
	
	//constructor
	private WebEndPointMessageListener(final WebEndPoint parentWebEndPoint) {
		
		GlobalValidator.assertThat(parentWebEndPoint).thatIsNamed("parent WebEndPoint").isNotNull();
		
		this.parentWebEndPoint = parentWebEndPoint;
		
		start();
	}
	
	//method
	@Override
	protected void run() {
		while (parentWebEndPoint.isOpen()) {
			receiveMessage();
		}
	}
	
	//method
	private void receiveMessage() {
		
		final var message =
		new WebSocketCompleteMessage(
			parentWebEndPoint::isOpen,
			parentWebEndPoint.getRefInputStream(),
			parentWebEndPoint::receiveControlFrame
		)
		.getMessage();
		
		receiveMessage(message);
	}
	
	//method
	private void receiveMessage(final String message) {
		
		//A web socket can send frames that contain a payload of length 0 resp. an empty message.
		if (!message.isEmpty()) {
			parentWebEndPoint.receiveRawMessageInBackground(message);
		}
	}
}
