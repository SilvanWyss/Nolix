package ch.nolix.systemTutorial.consoleClientTutorial;

//own imports
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.system.client.Application;
import ch.nolix.system.consoleClient.BackConsoleClient;
import ch.nolix.system.consoleClient.BackConsoleClientSession;
import ch.nolix.system.consoleClient.FrontConsoleClient;
import ch.nolix.templates.consoleClientLooks.BlackRedConsoleClientLook;

/**
 * The {@link BackConsoleClientTutorial} is a tutorial for {@link BackConsoleClient}s.
 * Of the {@link BackConsoleClientTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 80
 */
public final class BackConsoleClientTutorial {
	
	/**
	 * 1. Creates a {@link Application} for {@link BackConsoleClient}s.
	 * 2. Creates a {@link FrontConsoleClient} that will connect to the application.
	 * 
	 * @param arguments
	 */
	public static void main(final String[] arguments) {
		
		//Creates an Application.
		final var application
		= new Application<BackConsoleClient>(
			"MyApplication",
			BackConsoleClient.class,
			MainSession.class
		);
		
		//Creates a FrontConsoleClient that will connect to application.
		new FrontConsoleClient(application);
	}
	
	private static final class MainSession extends BackConsoleClientSession {
		
		@Override
		public void initialize() {
			
			setLook(new BlackRedConsoleClientLook());
			
			writeLineToInfoPanel(
				"This is the info panel of the console.",
				"On the info panel informatiosn can be displayed.",
				"The info panel cannot be edited."
			);
			
			writeLineToConsole("Enter your name.");
			final String name = readLineFromConsole();
			writeLineToConsole("Hello " + name + ".");
						
			boolean answerIsValid = false;
			do {
				
				writeLineToConsole("Are you fine? Press y for yes, press n for no.");
				
				switch (readCharacterFromConsole()) {
					case 'y':
						answerIsValid = true;
						writeLineToConsole("Oh good!");
						break;
					case 'n':
						answerIsValid = true;
						writeLineToConsole("Oh sad!");
						break;
					default:
				}
			} while (!answerIsValid);
			
			Sequencer.waitForASecond();
			
			writeEmptyLineToConsole();
			writeLineToConsole("Press enter to quit the program.");
			readEnterFromConsole();
			
			getParentClient().close();
		}
	}
	
	/**
	 * Avoids that an instance of the {@link BackConsoleClientTutorial} can be created.
	 */
	private BackConsoleClientTutorial() {}
}
