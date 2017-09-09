//package declaration
package ch.nolix.systemTutorial.consoleClientTutorial;

//own imports
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.system.client.Session;
import ch.nolix.system.client.StandardApplication;
import ch.nolix.system.consoleClient.ConsoleBackClient;
import ch.nolix.system.consoleClient.ConsoleFrontClient;

//class
/**
 * This class provides a tutorial for the ConsoleClient class.
 * 
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 90
 */
public final class ConsoleClientTutorial {

	//main method
	/**
	 * 1. Creates an application for console back clients.
	 * 2. Creates a console front client that connects to the application.
	 * 
	 * @param arguments
	 */
	@SuppressWarnings("resource")
	public static void main(final String[] arguments) {
		
		//Creates application.
		final StandardApplication<ConsoleBackClient> application
		= new StandardApplication<ConsoleBackClient>(
			"MyApplication",
			MainSession.class
		);
		
		//Creates console front client that connects to the application.
		new ConsoleFrontClient(application);
	}
	
	//inner class
	private static final class MainSession extends Session<ConsoleBackClient> {
		
		//method
		/**
		 * Initializes this main session.
		 */
		public void initialize() {
			
			getRefClient().writeLineToInfoPanel(
				"This is the info panel of the console.",
				"On the info panel informatiosn can be displayed.",
				"The info panel cannot be edited."
			);
			
			Sequencer.waitForMilliseconds(100);
			getRefClient().writeLineToConsole("Enter your name.");
			final String name = getRefClient().readLineFromConsole();
			
			Sequencer.waitForMilliseconds(100);
			getRefClient().writeLineToConsole("Hello " + name + ".");
						
			boolean answerIsValid = false;
			do {
				
				Sequencer.waitForMilliseconds(100);
				getRefClient().writeLineToConsole("Are you fine? (Press y for yes, press n for no.)");
				
				final char answer = getRefClient().readCharacterFromConsole();
				
				//Enumerates the received answer.
				switch (answer) {
					case 'y':
						answerIsValid = true;
						getRefClient().writeLineToConsole("Oh good!");
						break;
					case 'n':
						answerIsValid = true;
						getRefClient().writeLineToConsole("Oh sad!");
						break;
				}
			} while (!answerIsValid);
		}
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private ConsoleClientTutorial() {}
}