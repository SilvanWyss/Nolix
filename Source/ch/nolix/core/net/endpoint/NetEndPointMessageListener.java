//package declaration
package ch.nolix.core.net.endpoint;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalInputStreamHelper;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.worker.Worker;

//class
final class NetEndPointMessageListener extends Worker {
	
	//attributes
	private final NetEndPoint parentNetEndPoint;
		
	//constructor
	public NetEndPointMessageListener(final NetEndPoint parentNetEndPoint) {

		GlobalValidator.assertThat(parentNetEndPoint).thatIsNamed("parent NetEndPoint").isNotNull();
		
		this.parentNetEndPoint = parentNetEndPoint;
	}
	
	//method
	@Override
	protected void run() {
		while (parentNetEndPoint.isOpen()) {
			
			final var line = GlobalInputStreamHelper.readLineFrom(parentNetEndPoint.getRefInputStream());
			
			if (line.isEmpty()) {
				parentNetEndPoint.close();
				break;
			}
			
			parentNetEndPoint.receiveRawMessageInBackground(line);
		}
	}
}
