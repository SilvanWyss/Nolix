//package declaration
package ch.nolix.systemTutorial.consoleClientTutorial;

//own imports
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.system.client.Application;
import ch.nolix.system.client.Session;
import ch.nolix.system.consoleClient.BackConsoleClient;
import ch.nolix.system.consoleClient.FrontConsoleClient;
import ch.nolix.templates.frontConsoleClientLooks.BlackRedFrontConsoleClientLook;

//class
/**
 * This class provides a tutorial for the console client class.
 * 
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 90
 */
public final class BackConsoleClientTutorial {

	//main method
	/**
	 * 1. Creates an application for console back clients.
	 * 2. Creates a console front client that will connect to the application.
	 * 
	 * @param arguments
	 */
	public static void main(final String[] arguments) {
		
		//Creates an application.
		final var application
		= new Application<BackConsoleClient>(
			"MyApplication",
			BackConsoleClient.class,
			MainSession.class
		);
		
		//Creates a console front client that will connect to the application.
		new FrontConsoleClient(application);
	}
	
	//inner class
	private static final class MainSession extends Session<BackConsoleClient> {
		
		//method
		/**
		 * Initializes this main session.
		 */
		@Override
		public void initialize() {
			
			getParentClient().setDesign(new BlackRedFrontConsoleClientLook());
			
			getParentClient().writeLineToInfoPanel(
				"This is the info panel of the console.",
				"On the info panel informatiosn can be displayed.",
				"The info panel cannot be edited."
			);
			
			getParentClient().writeLineToConsole("Enter your name.");
			final String name = getParentClient().readLineFromConsole();
			getParentClient().writeLineToConsole("Hello " + name + ".");
						
			boolean answerIsValid = false;
			do {
				
				getParentClient().writeLineToConsole("Are you fine? Press y for yes, press n for no.");
				
				switch (getParentClient().readCharacterFromConsole()) {
					case 'y':
						answerIsValid = true;
						getParentClient().writeLineToConsole("Oh good!");
						break;
					case 'n':
						answerIsValid = true;
						getParentClient().writeLineToConsole("Oh sad!");
						break;
					default:
				}
			} while (!answerIsValid);
			
			Sequencer.waitForASecond();
			
			getParentClient()
			.writeEmptyLineToConsole()
			.writeLineToConsole("Press enter to quit the program.")
			.readEnterFromConsole();
			
			getParentClient().quit();
		}
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private BackConsoleClientTutorial() {}
}
