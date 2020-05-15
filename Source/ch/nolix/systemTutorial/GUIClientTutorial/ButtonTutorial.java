package ch.nolix.systemTutorial.GUIClientTutorial;

import ch.nolix.element.GUI.CursorIcon;
import ch.nolix.element.color.Color;
import ch.nolix.element.elementEnum.ExtendedContentPosition;
import ch.nolix.element.widget.Button;
import ch.nolix.system.GUIClient.BackGUIClient;
import ch.nolix.system.GUIClient.BackGUIClientSession;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.client.Application;

/**
 * The {@link ButtonTutorial} is a tutorial for {@link BackGUIClient}s.
 * Of the {@link ButtonTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 80
 */
public final class ButtonTutorial {
	
	/**
	 * 1. Creates a {@link Application} for {@link BackGUIClient}s.
	 * 2. Creates a front GUI client that will connect to the application.
	 * 
	 * @param arguments
	 */
	public static void main(String[] args) {
		
		//Creates an Application for BackGUIClients.
		final var application = new Application<BackGUIClient>("Application", BackGUIClient.class, MainSession.class);
		
		//Creates a FrontGUIClient that will connect to the Application.
		new FrontGUIClient(application);
	}
	
	private static final class MainSession extends BackGUIClientSession {
		
		private int counter = 1;
		
		@Override
		protected void initializeStage2() {
			
			//Sets the title of the GUI of the current MainSession.
			getRefGUI().setTitle("Back GUI Client Tutorial");
			
			//Creates a Button.
			final var button =
			new Button()
			.setText("Change background color")
			.setLeftMouseButtonPressCommand(this::changeColor);
			
			//Configures the look of the Button.
			button
			.setCustomCursorIcon(CursorIcon.Hand)
			.applyOnBaseLook(
				bl ->
				bl
				.setBorderThicknesses(5)
				.setBorderColors(Color.DARK_BLUE)
				.setBackgroundColor(Color.WHITE_SMOKE)
				.setPaddings(5)
				.setTextSize(50)
				.setTextColor(Color.DARK_BLUE)
			)
			.applyOnHoverLook(hl -> hl.setBackgroundColor(Color.LIGHT_GREY))
			.applyOnFocusLook(fl -> fl.setBackgroundColor(Color.LIGHT_GREY));
			
			//Adds the Button to the GUI of the current MainSession.
			getRefGUI().addLayerOnTop(ExtendedContentPosition.Top, button);
		}
		
		private void changeColor() {
			
			getRefGUI().setBackgroundColor(counter % 2 == 0 ? Color.WHITE : Color.LIGHT_GREEN);
			
			counter++;
		}
	}
	
	/**
	 * Avoids that an instance of the {@link ButtonTutorial} can be created.
	 */
	private ButtonTutorial() {}
}
