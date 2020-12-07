//package declaration
package ch.nolix.common.endpoint2;

//own imports
import ch.nolix.common.commontypehelper.InputStreamHelper;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.worker.Worker;

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
