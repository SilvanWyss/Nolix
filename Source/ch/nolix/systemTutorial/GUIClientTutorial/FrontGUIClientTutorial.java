package ch.nolix.systemTutorial.GUIClientTutorial;

import ch.nolix.element.GUI.CursorIcon;
import ch.nolix.element.GUI.ValueCatalogue;
import ch.nolix.element.color.Color;
import ch.nolix.element.elementEnums.ExtendedContentPosition;
import ch.nolix.element.widgets.Button;
import ch.nolix.system.GUIClient.BackGUIClient;
import ch.nolix.system.GUIClient.BackGUIClientSession;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.client.Application;

/**
 * The {@link FrontGUIClientTutorial} is a tutorial for {@link BackGUIClient}s.
 * Of the {@link FrontGUIClientTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 80
 */
public final class FrontGUIClientTutorial {
	
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
		public void initialize() {
			
			//Sets the title of the GUI of the current MainSession.
			getRefGUI().setTitle("Back GUI Client Tutorial");
			
			//Sets the background color of the GUI of the current MainSession.
			getRefGUI().setBackgroundColor(Color.GREEN);
			
			//Creates a Button.
			final var button =
			new Button()
			.setText("Change color")
			.setLeftMouseButtonPressCommand(this::changeColor)
			.setCustomCursorIcon(CursorIcon.Hand);
			
			//Configures the base look of the Button.
			button
			.getRefBaseLook()
			.setBackgroundColor(Color.LIGHT_GREY)
			.setPaddings(10)
			.setTextSize(ValueCatalogue.MEDIUM_TEXT_SIZE);
			
			//Configures the hover look of the Button.
			button
			.getRefHoverLook()
			.setTextSize(ValueCatalogue.BIG_TEXT_SIZE);
			
			//Adds the Button to the GUI of the current MainSession.
			getRefGUI().addLayerOnTop(ExtendedContentPosition.Center, button);
		}
		
		private void changeColor() {
			
			getRefGUI().setBackgroundColor(counter % 2 == 0 ? Color.GREEN : Color.RED);
			
			counter++;
		}
	}
	
	/**
	 * Avoids that an instance of the {@link FrontGUIClientTutorial} can be created.
	 */
	private FrontGUIClientTutorial() {}
}
