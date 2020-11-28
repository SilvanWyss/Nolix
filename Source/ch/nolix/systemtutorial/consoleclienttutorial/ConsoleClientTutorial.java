package ch.nolix.systemtutorial.consoleclienttutorial;

import ch.nolix.system.client.Application;
import ch.nolix.system.consoleclient.BackConsoleClient;
import ch.nolix.system.consoleclient.BackConsoleClientSession;
import ch.nolix.system.consoleclient.FrontConsoleClient;
import ch.nolix.template.consoleclientlook.BlackRedConsoleClientLook;

/**
 * The {@link ConsoleClientTutorial} is a tutorial for {@link BackConsoleClient}s.
 * Of the {@link ConsoleClientTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 70
 */
public final class ConsoleClientTutorial {
	
	/**
	 * 1. Creates a {@link Application} for {@link BackConsoleClient}s.
	 * 2. Creates a {@link FrontConsoleClient} that will connect to the {@link Application}.
	 * 
	 * @param arguments
	 */
	public static void main(final String[] arguments) {
		
		//Creates an Application.
		final var application = new Application<BackConsoleClient>("MyApplication", MainSession.class);
		
		//Creates a FrontConsoleClient that will connect to the Application.
		new FrontConsoleClient(application);
	}
	
	private static final class MainSession extends BackConsoleClientSession {
		
		@Override
		protected void initializeStage3() {
			
			setLook(new BlackRedConsoleClientLook());
						
			writeLineToInfoPanel(
				"This is the info panel of the console.",
				"On the info panel informatiosn can be displayed.",
				"The info panel cannot be edited."
			);
			
			writeLineToConsole("Enter your name.");
			final var name = readLineFromConsole();
			writeLineToConsole("Hello " + name + ".");
			
			var answerIsValid = false;
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
			
			writeLineToConsole("Press enter to quit the program.");
			readEnterFromConsole();
			pop();
		}
	}
	
	/**
	 * Avoids that an instance of the {@link ConsoleClientTutorial} can be created.
	 */
	private ConsoleClientTutorial() {}
}
