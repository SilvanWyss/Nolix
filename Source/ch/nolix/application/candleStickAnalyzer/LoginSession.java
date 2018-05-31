//package declaration
package ch.nolix.application.candlestickAnalyzer;

//own imports
import ch.nolix.system.client.Session;
import ch.nolix.system.consoleClient.BackConsoleClient;

//class
/**
* @author Silvan Wyss
* @month 2017-09
* @lines 30
*/
public final class LoginSession extends Session<BackConsoleClient> {

	//method
	/**
	 * Initializes this login session.
	 */
	public void initialize() {
		
		//Sets the title of the console.
		getParentClient().setTitle(CandlestickAnalyzer.TITLE);
		
		//Asks for the password.
		getParentClient().writeLineToConsole("Enter the password.");
		while (getParentClient().readSecretLineFromConsole().hashCode() != -1148684527) {
			getParentClient().writeLineToConsole("Wrong password. Try again.");
		}
		
		getParentClient().setSession(new MainSession());
	}
}
