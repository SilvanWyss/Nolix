//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.element.widget.Label;
import ch.nolix.element.widget.LabelRole;
import ch.nolix.system.GUIClient.BackGUIClientSession;

//class
public final class StartSession extends BackGUIClientSession {
	
	//constant
	private static final String MESSAGE = "Creating initial data...";
	
	//method
	@Override
	protected void initializeStage2() {
		
		getRefGUI().addLayerOnTop(new Label(MESSAGE).setRole(LabelRole.MainText));
		
		final var parentApplication = getParentApplication().as(DatabaseApplication.class);
		
		Sequencer
		.asSoonAs(parentApplication::isReady)
		
		//TODO: runInBackgrond(() -> setNext(new LoginSession()))
		.runInBackground(() -> push(new LoginSession()));
	}
}
