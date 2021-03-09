//package declaration
package ch.nolix.common.net.endpoint;

import ch.nolix.common.commontype.commontypehelper.InputStreamHelper;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.programcontrol.worker.Worker;

//class
final class NetEndPointMessageListener extends Worker {
	
	//attributes
	private final NetEndPoint parentNetEndPoint;
		
	//constructor
	public NetEndPointMessageListener(final NetEndPoint parentNetEndPoint) {

		Validator.assertThat(parentNetEndPoint).thatIsNamed("parent NetEndPoint").isNotNull();
		
		this.parentNetEndPoint = parentNetEndPoint;
	}
	
	//method
	@Override
	protected void run() {
		while (parentNetEndPoint.isOpen()) {
			
			final var line = InputStreamHelper.readLineFrom(parentNetEndPoint.getRefInputStream());
			
			if (line.isEmpty()) {
				parentNetEndPoint.close();
				break;
			}
			
			parentNetEndPoint.receiveRawMessageInBackground(line);
		}
	}
}
