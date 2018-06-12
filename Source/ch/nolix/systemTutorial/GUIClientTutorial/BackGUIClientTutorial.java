//package declaration
package ch.nolix.systemTutorial.GUIClientTutorial;

//own imports
import ch.nolix.element.GUI.Button;
import ch.nolix.element.GUI.ContentPosition;
import ch.nolix.element.GUI.CursorIcon;
import ch.nolix.element.GUI.ValueCatalogue;
import ch.nolix.element.color.Color;
import ch.nolix.system.GUIClient.BackGUIClient;
import ch.nolix.system.GUIClient.BackGUISession;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.client.Application;

//class
/**
 * The {@link BackGUIClientTutorial} is a tutorial for a {@link BackGUIClient}.
 * Of the {@link BackGUIClientTutorial} no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 100
 */
public final class BackGUIClientTutorial {

	//main method
	/**
	 * 1. Creates a an application for back GUI clients.
	 * 2. Creates a front GUI client that will connect to the application.
	 * 
	 * @param arguments
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates an application.
		final var application
		= new Application<BackGUIClient>(
			"Application",
			BackGUIClient.class,
			MainSession.class
		);
		
		//Creates a front GUI client that will to the application.
		new FrontGUIClient(application);
	}
	
	//inner class
	private static final class MainSession extends BackGUISession {

		//attribute
		private int counter = 1;
		
		//method
		public void initialize() {			
			
			final var button =
			new Button()
			.setText("Change color")
			.setLeftMouseButtonPressCommand(() -> changeColor())
			.setCursorIcon(CursorIcon.Hand);			
						
			button
			.getRefBaseLook()
			.setBackgroundColor(Color.LIGHT_GREY)
			.setPaddings(10)
			.setTextSize(ValueCatalogue.MEDIUM_TEXT_SIZE);
			
			button
			.getRefHoverLook()
			.setTextSize(ValueCatalogue.BIG_TEXT_SIZE);
			
			button
			.getRefHoverFocusLook()
			.setTextSize(ValueCatalogue.BIG_TEXT_SIZE);
			
			getRefGUI()
			.setTitle("Back GUI Client Tutorial")
			.setBackgroundColor(Color.GREEN)
			.setContentPosition(ContentPosition.Center)
			.setRootWidget(button);	
		}
		
		//method
		public void changeColor() {
						
			if (counter % 2 == 0) {
				getRefGUI().setBackgroundColor(Color.GREEN);
			}
			else {
				getRefGUI().setBackgroundColor(Color.BLUE);
			}
			
			counter++;
		}
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link BackGUIClientTutorial} can be created.
	 */
	private BackGUIClientTutorial() {}
}
