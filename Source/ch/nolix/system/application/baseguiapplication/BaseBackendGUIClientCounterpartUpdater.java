//package declaration
package ch.nolix.system.application.baseguiapplication;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//class
final class BaseBackendGUIClientCounterpartUpdater {
	
	//attribute
	private final BaseBackendGUIClient<?, ?> parentBackGUIClient;
	
	//attribute
	private final BaseBackendGUIClientGUIUpdateCommandCreator updateCanvasGUICommandCreator
	= new BaseBackendGUIClientGUIUpdateCommandCreator();
	
	//constructor
	public BaseBackendGUIClientCounterpartUpdater(final BaseBackendGUIClient<?, ?> parentBackGUIClient) {
		
		GlobalValidator.assertThat(parentBackGUIClient).thatIsNamed("parent BackGUIClient").isNotNull();
		
		this.parentBackGUIClient = parentBackGUIClient;
	}
	
	//method
	public void updateCounterpart() {
		
		final var updateCommands = updateCanvasGUICommandCreator.createUpdateCommandsFor(parentBackGUIClient.getRefGUI());
		
		if (updateCommands.containsAny()) {
			parentBackGUIClient.internalRunOnCounterpart(updateCommands);
		}
	}
}
