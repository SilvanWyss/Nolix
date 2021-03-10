//package declaration
package ch.nolix.system.databaseapplication.main;

import ch.nolix.common.programcontrol.sequencer.Sequencer;
import ch.nolix.element.gui.widget.Label;
import ch.nolix.element.gui.widget.LabelRole;
import ch.nolix.system.client.guiclient.BackGUIClientSession;

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
