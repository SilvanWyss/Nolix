//package declaration
package ch.nolix.systemTutorial.GUIClientTutorial;

//own imports
import ch.nolix.element.GUI.Button;
import ch.nolix.element.GUI.ContentPosition;
import ch.nolix.element.GUI.CursorIcon;
import ch.nolix.element.basic.Color;
import ch.nolix.system.GUIClient.GUIBackClient;
import ch.nolix.system.GUIClient.GUIFrontClient;
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
		final StandardApplication<GUIBackClient> application
		= new StandardApplication<GUIBackClient>(
			"Application",
			MainSession.class
		);
		
		//Creates front dialog client that connects to the application.
		new GUIFrontClient(application);
	}
	
	//inner class
	private static final class MainSession extends Session<GUIBackClient> {

		//attribute
		private Color backgroundColor = Color.BLUE;
		
		//method
		/**
		 * Initializes this main session.
		 */
		public void initialize() {
			
			getRefClient().getRefGUI()
			.setTitle("Dialog Client Tutorial")
			.removeConfiguration()
			.setContentPosition(ContentPosition.Center)
			.setBackgroundColor(backgroundColor);
			
			final Button changeColorbutton
			= new Button()
			.setText("Change color")
			.setLeftMouseButtonPressCommand("ChangeColor")
			.setCursorIcon(CursorIcon.Hand);			
						
			changeColorbutton.getRefNormalStructure()
			.setPaddings(10)
			.setBackgroundColor(Color.AQUA);
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
				backgroundColor = Color.FOREST_GREEN;
			}
			else {
				backgroundColor = Color.BLUE;
			}
			
			getRefClient().getRefGUI().setBackgroundColor(backgroundColor);
		}
	}

	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private GUIClientTutorial() {}
}
