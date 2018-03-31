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
		getRefClient().setTitle(CandlestickAnalyzer.TITLE);
		
		//Asks for the password.
		getRefClient().writeLineToConsole("Enter the password.");
		while (getRefClient().readSecretLineFromConsole().hashCode() != -1148684527) {
			getRefClient().writeLineToConsole("Wrong password. Try again.");
		}
		
		getRefClient().setSession(new MainSession());
	}
}
