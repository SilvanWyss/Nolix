//package declaration
package ch.nolix.system.consoleClient;

//own imports
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.system.application.Session;

//abstract class
/**
 * A console client session is a session of a console client.
 * 
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 10
 */
public abstract class ConsoleClientSession extends Session<ConsoleClient> {

	//method
	/**
	 * Initializes this console client session.
	 */
	public final void initialize() {
		Sequencer.runInBackground(() -> process()); 
	}
	
	//abstract method
	/**
	 * Processes this console client session.
	 */
	protected abstract void process();
	
	protected void readNextEnterOfConsole() {
		getRefClient().readNextEnterOfConsole();
	}
}
