//package declaration
package ch.nolix.system.dialogClient;

//own imports
import ch.nolix.common.application.Application;
import ch.nolix.common.application.Client;
import ch.nolix.common.application.Session;
import ch.nolix.common.container.List;
import ch.nolix.common.duplexController.DuplexController;
import ch.nolix.common.specification.Specification;
import ch.nolix.common.specification.Statement;
import ch.nolix.common.zetaValidator.ZetaValidator;
import ch.nolix.element.dialog.Dialog;
import ch.nolix.element.dialog.MockDialog;

//class
/**
 * A dialog client is a client that provides a dialog.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 200
 */
public final class DialogClient extends Client<DialogClient> {
	
	//commands
	final static String RESET_DIALOG_COMMAND = "ResetDialog";
	final static String RESET_OTHER_SIDE_DIALOG_COMMAND = "ResetOtherSideDialog";
	
	//attribute
	private Dialog<?> dialog;

	//constructor
	/**
	 * Creates new dialog client that:
	 * -Will connect to the given target application.
	 * -Has the given dialog.
	 * 
	 * @param dialog
	 * @param targetApplication
	 * @throws NullArgumentException if the given target application is null.
	 * @throws NullArgumentException if the given dialog is null.
	 */
	public DialogClient(final Application<DialogClient> targetApplication, final Dialog<?> dialog) {
		
		//Calls constructor of the base class.
		super(targetApplication, c -> c.setDialog(dialog));
	}
	
	//constructor
	/**
	 * Creates new dialog client that will connect to the given target application on the given port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param targetApplication
	 * @throws NullArgumentException if the given target application is null.
	 * @throws EmptyArgumentException if the given target application is empty.
	 */
	public DialogClient(
		final String ip,
		final int port,
		final String targetApplication
	) {
		
		//Calls constructor of the base class.
		super(ip, port, targetApplication, c -> c.setDialog(new MockDialog()));
	}
	
	//constructor
	/**
	 * Creates new dialog client that:
	 * -Will connect to the given target application on the given port on the machine with the given ip.
	 * -Has the given dialog.
	 * 
	 * @param ip
	 * @param port
	 * @param targetApplication
	 * @param dialog
	 * @throws NullArgumentException if the given target application is null.
	 * @throws EmptyArgumentException if the given target application is empty.
	 * @throws NullArgumentException if the given dialog is null.
	 */
	public DialogClient(
		final String ip,
		final int port,
		final String targetApplication,
		final Dialog<?> dialog
	) {
		
		//Calls constructor of the base class.
		super(ip, port, targetApplication, c -> c.setDialog(dialog));
	}
	
	//constructor
	/**
	 * Creates new dialog client with the given duplex controller and the given initial session.
	 * 
	 * @param duplexController
	 * @param initialSession
	 * @throws NullArgumentException if the given duplex controller is null.
	 * @throws NullArgumentException if the given initial session is null.
	 */
	public DialogClient(final DuplexController duplexController, final Session<DialogClient> initialSession) {
		
		//Calls constructor of the base class.
		super(duplexController, c -> c.setDialog(new MockDialog()), initialSession);
	}
	
	//method
	/**
	 * @return the dialog of this dialog client
	 */
	public Dialog<?> getRefDialog() {
		return dialog;
	}
	
	//method
	/**
	 * Invokes a run method of this dialog client.
	 * 
	 * @param runMethodCommand
	 */
	public void runLocally(final String runMethodCommand) {
		internal_invokeRunMethod(new Specification(runMethodCommand));
		internal_getRefDuplexController().run(RESET_DIALOG_COMMAND + "(" + dialog.getAttributes() + ")");
	}
	
	//method
	/**
	 * Sets the session of this dialog client.
	 * 
	 * @param session
	 * @throws NullArgumentException if the given session is null.
	 * @throws RuntimeException if the given session has already a client.
	 */
	public void setSession(final Session<DialogClient> session) {
		internal_setSessionAndInitializeSession(session);
	}
	
	//method
	/**
	 * Finishes the initialization of the session of this dialog client.
	 */
	protected void internal_finishSessionInitialization() {
		internal_getRefDuplexController().run(RESET_DIALOG_COMMAND + "(" + dialog.getAttributes() + ")");
	}
	
	//method
	/**
	 * Lets this client run the given command.
	 * 
	 * @param command
	 * @throws InvalidArgumentException if the given command is not valid.
	 */
	protected void internal_run(final Statement command) {
		
		//Enumerates the header of the given command.
		switch (command.getHeader()) {
			case RESET_DIALOG_COMMAND:
				resetDialog(command.getRefAttributes());
				break;
			case RESET_OTHER_SIDE_DIALOG_COMMAND:
				resetOtherSideDialog(command.getRefAttributes());
				break;
			default:
				
				//Calls method of the base class.
				super.internal_run(command);
		}
	}
	
	//method
	/**
	 * Resets the dialog of this dialog client with the given attributes.
	 * 
	 * @param attributes
	 */
	private void resetDialog(final List<Specification> attributes) {
		dialog.reset(attributes);
	}
	
	//method
	/**
	 * Resets the dialog of the other side of this dialog client with the given attributes.
	 * 
	 * @param attributes
	 */
	private void resetOtherSideDialog(final List<Specification> attributes) {
		internal_getRefDuplexController().run(DialogClient.RESET_DIALOG_COMMAND + "(" + dialog.getAttributes() + ")");
	}

	//method
	/**
	 * Sets the dialog of this dialog client.
	 * 
	 * @param dialog
	 * @throws NullArgumentException if the given dialog is null.
	 */
	private void setDialog(final Dialog<?> dialog) {
		
		//Checks if the given dialog is not null.
		ZetaValidator.supposeThat(dialog).thatIsInstanceOf(Dialog.class).isNotNull();
		
		//Sets the dialog of this dialog client.
		this.dialog = dialog;
	}
}
