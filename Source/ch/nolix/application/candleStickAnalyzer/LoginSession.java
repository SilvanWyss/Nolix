//package declaration
package ch.nolix.application.candleStickAnalyzer;

//own imports
import ch.nolix.system.client.Session;
import ch.nolix.system.consoleClient.ConsoleBackClient;

//class
/**
* @author Silvan Wyss
* @month 2017-09
* @lines 30
*/
public final class LoginSession extends Session<ConsoleBackClient> {

	//method
	/**
	 * Initializes this login session.
	 */
	public void initialize() {
		
		//Sets the title of the console.
		getRefClient().setTitle(CandleStickAnalyzer.TITLE);
		
		//Asks for the password.
		getRefClient().writeLineToConsole("Enter the password.");
		
		while (!getRefClient().readSecretLineFromConsole().equals("justin")) {
			getRefClient().writeLineToConsole("Wrong password. Try again.");
		}
		
		getRefClient().setSession(new MainSession());
	}
}
