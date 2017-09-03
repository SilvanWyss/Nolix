//package declaration
package ch.nolix.system.consoleClient;

//own import
import ch.nolix.system.client.Session;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 130
 */
public abstract class ConsoleBackClientSession
extends Session<ConsoleBackClient> {

	//method
	/**
	 * Clears the console
	 * of the counterpart of the client of this console back client session.
	 */
	public final void clearConsole() {		
		getRefClient().clearConsole();
	}
	
	//method
	/**
	 * Clears the info panel
	 * of the counterpart of the client of this console back client session.
	 */
	public final void clearInfoPanel() {	
		getRefClient().clearInfoPanel();
	}
	
	//method
	/**
	 * Lets the counterpart of the client of this console back client session
	 * open a file explorer.
	 */
	public final void openFileExplorer() {	
		getRefClient().openFileExplorer();
	}
	
	//method
	/**
	 * @return the next character from the console
	 * of the counterpart of the client of this console back client session.
	 */
	public final char readCharacterFromConsole() {
		return getRefClient().readCharacterFromConsole();
	}
	
	//method
	/**
	 * Reads the next enter from the console
	 * of the counterpart of the client of this console back client session.
	 */
	public void readEnterFromConsole() {
		getRefClient().readEnterFromConsole();
	}
	
	//method
	/**
	 * @return the next line from the console
	 * of the counterpart of the client of this console back client session.
	 */
	public final String readTextLineFromConsole() {
		return getRefClient().readLineFromConsole();
	}
	
	//method
	/**
	 * @return the next line, that is not empty, from the console
	 * of the counterpart of the client of this console back client session.
	 */
	public final String readNonEmptyTextLineFromConsole() {
		return getRefClient().readNonEmptyLineFromConsole();
	}
	
	//method
	/**
	 * Sets the title of the console
	 * of the counterpart of the client of this console back client session.
	 * 
	 * @param title
	 */
	public final void setTitle(final String title) {	
		getRefClient().setTitle(title);
	}
	
	//method
	/**
	 * Writes the given line to the console
	 * of the counterpart of the client of this console back client session.
	 * 
	 * @param line
	 */
	public final void writeTextLineToConsole(final String line) {		
		getRefClient().writeLineToConsole(line);
	}
	
	//method
	/**
	 * Writes the given lines to the console
	 * of the counterpart of the client of this console back client session.
	 * 
	 * @param lines
	 */
	public final void writeTextLineToConsole(final String... lines) {		
		getRefClient().writeLineToConsole(lines);
	}
	
	//method
	/**
	 * Writes the given line to the info panel
	 * of the counterpart of the client of this console back client session.
	 * 
	 * @param line
	 * @return this console back client session.
	 */
	public final void writeTextLineToInfoPanel(final String line) {	
		getRefClient().writeLineToInfoPanel(line);
	}
	
	//method
	/**
	 * Writes the given lines to the info panel
	 * of the counterpart of the client of this console back client session.
	 * 
	 * @param lines
	 */
	public final void writeNextLineToInfoPanel(final String... lines) {		
		getRefClient().writeLineToInfoPanel(lines);
	}
}
