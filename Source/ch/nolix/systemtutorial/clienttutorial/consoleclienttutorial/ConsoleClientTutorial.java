package ch.nolix.systemtutorial.clienttutorial.consoleclienttutorial;

import ch.nolix.system.client.base.Application;
import ch.nolix.system.client.consoleclient.BackConsoleClient;
import ch.nolix.system.client.consoleclient.BackConsoleClientSession;
import ch.nolix.system.client.consoleclient.FrontConsoleClient;
import ch.nolix.template.consoleclientlook.BlackRedConsoleClientLookCreator;

/**
 * The {@link ConsoleClientTutorial} is a tutorial for {@link BackConsoleClient}s.
 * Of the {@link ConsoleClientTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2017-08-20
 */
public final class ConsoleClientTutorial {
	
	/**
	 * 1. Creates a {@link Application} for {@link BackConsoleClient}s.
	 * 2. Creates a {@link FrontConsoleClient} that will connect to the {@link Application}.
	 * 
	 * @param arguments
	 */
	@SuppressWarnings("resource")
	public static void main(final String[] arguments) {
		
		//Creates an Application.
		final var application = new Application<BackConsoleClient>("MyApplication", MainSession.class);
		
		//Creates a FrontConsoleClient that will connect to the Application.
		new FrontConsoleClient(application);
	}
	
	private static final class MainSession extends BackConsoleClientSession {
		
		@Override
		protected void initializeBackConsoleClientSession() {
			
			setLook(new BlackRedConsoleClientLookCreator().createClientLook());
						
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
	 * Prevents that an instance of the {@link ConsoleClientTutorial} can be created.
	 */
	private ConsoleClientTutorial() {}
}
