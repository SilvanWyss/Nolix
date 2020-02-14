package ch.nolix.systemTutorial.GUIClientTutorial;

import ch.nolix.element.elementEnums.ExtendedContentPosition;
import ch.nolix.element.widgets.Label;
import ch.nolix.system.GUIClient.BackGUIClient;
import ch.nolix.system.GUIClient.BackGUIClientSession;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.client.Application;

/**
 * The {@link CursorPositionTutorial} is a tutorial for {@link FrontGUIClient}s.
 * Of the {@link CursorPositionTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2020-02
 * @lines 60
 */
public final class CursorPositionTutorial {
	
	/**
	 * 1. Creates a {@link Application} for {@link BackGUIClient}s.
	 * 2. Creates a {@link FrontGUIClient} that will connect to the {@link Application}.
	 * 
	 * @param arguments
	 */
	public static void main(String[] args) {
		
		//Creates an Application.
		final var application =	new Application<>("Application", BackGUIClient.class, MainSession.class);
		
		//Creates a FrontGUIClient that will connect to the Application.
		new FrontGUIClient(application);
	}
	
	private static final class MainSession extends BackGUIClientSession {
		
		private final Label cursorPositionLabel = new Label();
		
		@Override
		public void initialize() {
			
			//TODO: Add mouse move command to GUI.
			updateCursorPosition();
			cursorPositionLabel.setLeftMouseButtonPressCommand(this::updateCursorPosition);
			
			//Configures the look of the cursorPositionLabel.
			cursorPositionLabel.applyOnBaseLook(bl -> bl.setPaddings(5));
			
			//Adds the cursorPositionLabel to the GUI of the current BackGUIClientSession at the left top.
			getRefGUI().addLayerOnTop(ExtendedContentPosition.LeftTop, cursorPositionLabel);	
		}
		
		private void updateCursorPosition() {
			cursorPositionLabel.setText(
				"Cursor position: "
				+ getRefGUI().getViewAreaCursorXPosition()
				+ ", "
				+ getRefGUI().getViewAreaCursorYPosition()
			);
		}
	}
	
	/**
	 * Avoids that an instance of the {@link CursorPositionTutorial} can be created.
	 */
	private CursorPositionTutorial() {}
}
