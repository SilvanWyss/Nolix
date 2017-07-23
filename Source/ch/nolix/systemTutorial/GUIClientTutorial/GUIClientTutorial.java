//package declaration
package ch.nolix.systemTutorial.GUIClientTutorial;

//own imports
import ch.nolix.element.GUI.Button;
import ch.nolix.element.GUI.ContentPosition;
import ch.nolix.element.GUI.CursorIcon;
import ch.nolix.element.basic.Color;
import ch.nolix.element.data.BackgroundColor;
import ch.nolix.system.GUIClient.GUIClient;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.client.Session;
import ch.nolix.system.client.StandardApplication;

//class
/**
 * This class provides a tutorial for the dialog client class.
 * 
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 90
 */
public final class GUIClientTutorial {

	//main method
	/**
	 * 1. Creates an application for dialog clients.
	 * 2. Creates a front dialog client that connects to the application.
	 * 
	 * @param arguments
	 */
	public static void main(final String[] arguments) {
		
		//Creates application.
		final StandardApplication<GUIClient> application
		= new StandardApplication<GUIClient>(
			"Application",
			MainSession.class
		);
		
		//Creates front dialog client that connects to the application.
		new FrontGUIClient(application);
	}
	
	//inner class
	private static final class MainSession extends Session<GUIClient> {

		//attribute
		private int backgroundColor = Color.BLUE;
		
		//method
		/**
		 * Initializes this main session.
		 */
		public void initialize() {
			
			getRefClient().getRefGUI()
			.setTitle("Dialog Client Tutorial")
			.removeConfiguration()
			.setContentPosition(ContentPosition.Center)
			.setBackgroundColor(new BackgroundColor(backgroundColor));
			
			final Button changeColorbutton
			= new Button()
			.setText("Change color")
			.setLeftMouseButtonPressCommand("ChangeColor")
			.setCursorIcon(CursorIcon.Hand);			
						
			changeColorbutton.getRefNormalStructure()
			.setPadding(10)
			.setBackgroundColor(new BackgroundColor(Color.LIGHT_GREY));
			changeColorbutton.getRefHoverStructure().setTextSize(30);
			
			getRefClient().getRefGUI().setRootWidget(changeColorbutton);	
		}
		
		//method
		/**
		 * Changes the color of the dialog of the client of this main session.
		 */
		@SuppressWarnings("unused")
		public void ChangeColor() {
			
			if (backgroundColor == Color.BLUE) {
				backgroundColor = Color.RED;
			}
			else {
				backgroundColor = Color.BLUE;
			}
			
			getRefClient().getRefGUI().setBackgroundColor(new BackgroundColor(backgroundColor));
		}
	}

	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private GUIClientTutorial() {}
}
