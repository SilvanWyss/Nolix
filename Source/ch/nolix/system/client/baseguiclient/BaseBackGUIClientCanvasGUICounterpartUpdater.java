//package declaration
package ch.nolix.system.client.baseguiclient;

import ch.nolix.core.errorcontrol.validator.Validator;

//class
final class BaseBackGUIClientCanvasGUICounterpartUpdater implements IBackGUIClientCounterpartUpdater {
	
	//attribute
	private final BaseBackGUIClient<?> parentBackGUIClient;
	
	//attribute
	private final BaseBackGUIClientUpdateCanvasGUICommandCreator updateCanvasGUICommandCreator =
	new BaseBackGUIClientUpdateCanvasGUICommandCreator();
	
	//constructor
	public BaseBackGUIClientCanvasGUICounterpartUpdater(final BaseBackGUIClient<?> parentBackGUIClient) {
		
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
