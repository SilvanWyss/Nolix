//package declaration
package ch.nolix.systemTutorial.GUIClientTutorial;

//own imports
import ch.nolix.element.GUI.Button;
import ch.nolix.element.GUI.ContentPosition;
import ch.nolix.element.GUI.CursorIcon;
import ch.nolix.element.GUI.ValueCatalogue;
import ch.nolix.element.color.Color;
import ch.nolix.system.GUIClient.BackGUIClient;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.client.Session;
import ch.nolix.system.client.StandardApplication;

//class
/**
 * This class provides a tutorial for the GUI client class.
 * 
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 100
 */
public final class BackGUIClientTutorial {

	//main method
	/**
	 * 1. Creates an application for dialog clients.
	 * 2. Creates a front dialog client that will connect to the application.
	 * 
	 * @param arguments
	 */
	@SuppressWarnings("resource")
	public static void main(final String[] arguments) {
		
		//Creates an application.
		final StandardApplication<BackGUIClient> application
		= new StandardApplication<BackGUIClient>(
			"Application",
			MainSession.class
		);
		
		//Creates a front dialog client that will to the application.
		new FrontGUIClient(application);
	}
	
	//inner class
	private static final class MainSession extends Session<BackGUIClient> {

		//attribute
		private int counter = 1;
		
		//method
		/**
		 * Initializes this main session.
		 */
		public void initialize() {			
			
			final Button button =
			new Button()
			.setText("Change color")
			.setLeftMouseButtonPressCommand("ChangeColor")
			.setCursorIcon(CursorIcon.Hand);			
						
			button
			.getRefNormalStructure()
			.setBackgroundColor(Color.LIGHT_GREY)
			.setPaddings(10)
			.setTextSize(ValueCatalogue.MEDIUM_TEXT_SIZE);
			
			button
			.getRefHoverStructure()
			.setTextSize(ValueCatalogue.BIG_TEXT_SIZE);
			
			button
			.getRefHoverFocusStructure()
			.setTextSize(ValueCatalogue.BIG_TEXT_SIZE);
			
			getRefClient()
			.getRefGUI()
			.setTitle("Dialog Client Tutorial")
			.removeConfiguration()
			.setBackgroundColor(Color.GREEN)
			.setContentPosition(ContentPosition.Center)
			.setRootWidget(button);	
		}
		
		//method
		/**
		 * Changes the color of the GUI of the client of this main session.
		 */
		@SuppressWarnings("unused")
		public void ChangeColor() {
			
			if (counter % 2 == 0) {
				getRefClient().getRefGUI().setBackgroundColor(Color.GREEN);
			}
			else {
				getRefClient().getRefGUI().setBackgroundColor(Color.BLUE);
			}
			
			counter++;
		}
	}

	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private BackGUIClientTutorial() {}
}
