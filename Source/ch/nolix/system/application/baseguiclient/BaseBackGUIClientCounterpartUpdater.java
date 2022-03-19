//package declaration
package ch.nolix.system.application.baseguiclient;

//own imports
import ch.nolix.core.errorcontrol.validator.Validator;

//class
final class BaseBackGUIClientCounterpartUpdater {
	
	//attribute
	private final BaseBackGUIClient<?, ?> parentBackGUIClient;
	
	//attribute
	private final BaseBackGUIClientUpdateCanvasGUICommandCreator updateCanvasGUICommandCreator =
	new BaseBackGUIClientUpdateCanvasGUICommandCreator();
	
	//constructor
	public BaseBackGUIClientCounterpartUpdater(final BaseBackGUIClient<?, ?> parentBackGUIClient) {
		
		Validator.assertThat(parentBackGUIClient).thatIsNamed("parent BackGUIClient").isNotNull();
		
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
