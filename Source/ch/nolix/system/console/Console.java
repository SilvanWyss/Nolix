//package declaration
package ch.nolix.system.console;

//own import
import ch.nolix.common.application.Client;
import ch.nolix.common.controller.ILevel1Controller;
import ch.nolix.common.specification.Statement;
import ch.nolix.common.zetaValidator.ZetaValidator;
import ch.nolix.element.dialog.Dialog;
import ch.nolix.element.dialog.Frame;
import ch.nolix.element.dialog.TextBox;
import ch.nolix.element.dialog.VerticalStack;

//class
/**
 * A console is a client that provides a command display.
 * 
 * @author Silvan Wyss
 * @month 2017-02
 * @lines 10
 */
public final class Console extends Client<Console> {
	
	//attribute
	private Dialog<?> dialog;

	//constructor
	/**
	 * Creates new console that connects to the given target application on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param targetApplication
	 * @throws NullArgumentException if the given target application is null.
	 * @throws EmptyArgumentException if the given target application is empty.
	 */
	public Console(
		final String ip,
		final int port,
		final String targetApplication
	) {
		
		//Calls constructor of the base class.
		super(ip, port, targetApplication, c -> c.setDialog(new Frame()));
	}

	//method
	/**
	 * Finishes the initialization of the session of this console.
	 */
	protected void internal_finishSessionInitialization() {}
	
	protected Object internal_getData(final Statement request) {
		
		//Enumerates the given request.
		switch (request.toString()) {
			case ConsoleClient.READ_CHARACTER_COMMAND:
				return readCharacter();
			case ConsoleClient.READ_STRING_COMMAND:
				return readString();
		
			default:
				
				//Calls method of the base class.
				return super.internal_getData(request);
		}
	}
	
	//method
	/**
	 * Lets this client run the given command.
	 * 
	 * @param command
	 * @throws InvalidArgumentException if the given command is not valid.
	 */
	protected void internal_run(final Statement command) {
		
		//Enumerates the command of this console.
		switch (command.getHeader()) {
			case ConsoleClient.WRITE_LINE_COMMAND:
				writeLine(command.getOneAttributeToString());
				break;
			case ConsoleClient.READ_ENTER_COMMAND:
				readEnter();
				break;
			default:
				
				//Calls method of the base class.
				super.internal_run(command);
		}
	}
	
	//method
	/**
	 * Sets the dialog of this console.
	 * 
	 * @param dialog
	 * @throws NullArgumentException if the given dialog is null.
	 */
	private void setDialog(final Dialog<?> dialog) {
		
		//Checks if the given dialog is not null.
		ZetaValidator.supposeThat(dialog).thatIsInstanceOf(Dialog.class).isNotNull();
		
		this.dialog = dialog;
		
		dialog
		.setTitle(internal_getTargetApplication())
		.setRootRectangle(
			new VerticalStack(
				new TextBox()
			)	
		)
		.setController(
				
			//anonymous class
			new ILevel1Controller() {

				//method
				public void run(final Statement command) {
					internal_run(command);
				}
			}
		);
	}
	
	//method
	private char readCharacter() {
		//TODO
		return 0;
	}
	
	//method
	private void readEnter() {
		//TODO
	}
	
	//method
	private String readString() {
		//TODO
		return null;
	}
	
	//method
	/**
	 * Lets this console write the given line.
	 * 
	 * @param line
	 */
	private void writeLine(final String line) {
		//TODO
	}
}
