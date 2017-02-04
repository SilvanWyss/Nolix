//package declaration
package ch.nolix.systemTutorial.dialogClientTutorial;

//own imports
import ch.nolix.common.application.Session;
import ch.nolix.common.application.StandardApplication;
import ch.nolix.element.basic.Color;
import ch.nolix.element.dialog.Button;
import ch.nolix.element.dialog.ContentOrientation;
import ch.nolix.system.dialogClient.DialogClient;
import ch.nolix.system.dialogClient.FrontDialogClient;

//class
/**
 * This class provides a tutorial for the dialog client class.
 * 
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 90
 */
public final class DialogClientTutorial {

	//main method
	/**
	 * 1. Creates an application for dialog clients.
	 * 2. Creates a dialog client that connects to the application.
	 * 
	 * @param arguments
	 */
	public static void main(final String[] arguments) {
		
		//Creates application.
		final StandardApplication<DialogClient> application
		= new StandardApplication<DialogClient>(
			"Application",
			MainSession.class
		);
		
		//Creates front dialog client that connects to the application.
		new FrontDialogClient(application);
	}
	
	//inner class
	private static class MainSession extends Session<DialogClient> {

		//attribute
		private int backgroundColor = Color.BLUE;
		
		//method
		/**
		 * Initializes this main session.
		 */
		public void initialize() {
			
			getRefClient().getRefDialog()
			.setTitle("Dialog Client Tutorial")
			.removeConfiguration()
			.setContentOrientation(ContentOrientation.Center)
			.setBackgroundColor(backgroundColor);
			
			final Button changeColorbutton
			= new Button()
			.setPadding(10)
			.setText("Change color")
			.setLeftClickCommand("ChangeColor");	
			changeColorbutton.getRefNormalStructure().setBackgroundColor(Color.LIGHT_GREY);
			changeColorbutton.getRefHoverStructure().setTextSize(30);
			getRefClient().getRefDialog().setRootRectangle(changeColorbutton);	
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
			
			getRefClient().getRefDialog().setBackgroundColor(backgroundColor);
		}
	}

	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private DialogClientTutorial() {}
}
