//package declaration
package ch.nolix.common.net.endpoint;

import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.net.websocket.WebSocketCompleteMessage;
import ch.nolix.common.worker.Worker;

//class
final class WebEndPointMessageListener extends Worker {
	
	//attribute
	private final WebEndPoint parentWebEndPoint;
	
	//constructor
	public WebEndPointMessageListener(final WebEndPoint parentWebEndPoint) {
		
		Validator.assertThat(parentWebEndPoint).thatIsNamed("parent WebEndPoint").isNotNull();
		
		this.parentWebEndPoint = parentWebEndPoint;
	}
	
	@Override
	protected void run() {
		while (parentWebEndPoint.isOpen()) {
			parentWebEndPoint.receiveRawMessageInBackground(			
				new WebSocketCompleteMessage(
					parentWebEndPoint::isOpen,
					parentWebEndPoint.getRefInputStream(),
					parentWebEndPoint::receiveControlFrame
				)
				.getMessage()
			);
		}
	}
}
