/*
 * file:	DialogClient.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	60
 */

//package declaration
package ch.nolix.system.application;

//own imports
import ch.nolix.common.duplexController.DuplexController;
import ch.nolix.common.specification.Specification;
import ch.nolix.element.dialog.Dialog;
import ch.nolix.element.dialog.Frame;
import ch.nolix.element.dialog.MockDialog;

//class
/**
 * A dialog client is a client that provides an accessible dialog.
 */
public final class DialogClient extends Client<DialogClient> {
	
	//attribute
	private Dialog<?> dialog;

	//constructor
	/**
	 * Creates new dialog client with the given dialog that will connect to the given target applicaiton.
	 * 
	 * @param dialog
	 * @param targetApplication
	 */
	public DialogClient(Dialog<?> dialog, Application<DialogClient> targetApplication) {
		
		//Calls constructor of the base class.
		super(dialog, targetApplication);
	}
	
	//constructor
	/**
	 * Creates new dialog client that will connect to the given target application on the give port on the machine with the given ip.
	 * 
	 * @param ip
	 * @param port
	 * @param targetApplication
	 */
	public DialogClient(String ip, int port, String targetApplication) {
		
		//Calls constructor of the base class.
		super(ip, port, targetApplication, new Frame());
	}
	
	//constructor
	/**
	 * Creates new dialog client with the given dialog that will connect to the given target application on the given port on the machine with the given ip.
	 * 
	 * @param dialog
	 * @param ip
	 * @param port
	 * @param targetApplication
	 */
	public DialogClient(Dialog<?> dialog, String ip, int port, String targetApplication) {
		
		//Calls constructor of the base class.
		super(ip, port, targetApplication, dialog);
	}
	
	//constructor
	/**
	 * Creates new dialog client with the given duplex controller and the given initial session.
	 * 
	 * @param duplexController
	 * @param initialSession
	 */
	public DialogClient(DuplexController duplexController, Session<DialogClient> initialSession) {
		
		//Calls constructor of the base class.
		super(new MockDialog(), duplexController, initialSession);
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
	 * Initializes this dialog client with the given object.
	 * 
	 * @param object
	 */
	protected void initialize(final Object object) {
		
		dialog = (Dialog<?>)object;
	}

	//method
	protected Specification createUpdateSpecification() {
		return dialog.getSpecification();
	}

	//method
	protected void update(Specification updateSpecification) {
		dialog.reset(updateSpecification.getRefAttributes());
		dialog.update();
	}
}
