//package declaration
package ch.nolix.system.database.databaseapplication;

//own imports
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.element.widget.Label;
import ch.nolix.element.widget.LabelRole;
import ch.nolix.system.guiclient.BackGUIClientSession;

//class
public final class StartSession extends BackGUIClientSession {
	
	//constant
	private static final String MESSAGE = "Creating initial data...";
	
	//method
	@Override
	protected void initializeBaseBackGUIClientSession() {
		
		getRefGUI().addLayerOnTop(new Label().setText(MESSAGE).setRole(LabelRole.MAIN_TEXT));
		
		final var parentApplication = getParentApplication().as(DatabaseApplication.class);
		
		Sequencer
		.asSoonAs(parentApplication::isReady)
		
		//TODO: runInBackgrond(() -> setNext(new LoginSession()))
		.runInBackground(() -> push(new LoginSession()));
	}
}
