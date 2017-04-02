/*
 * file:	DuplexController.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	130
 */

//package declaration
package ch.nolix.common.duplexController;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.controller.ILevel2Controller;
import ch.nolix.common.exception.UnexistingAttributeException;
import ch.nolix.common.interfaces.Abortable;
import ch.nolix.common.specification.Specification;
import ch.nolix.common.util.Validator;

//class
public abstract class DuplexController
implements ILevel2Controller, Abortable {
	
	//default value
	public static final int DEFAULT_TIMEOUT_IN_MILLISECONDS = 10000;

	//attribute
	private int timeoutInMilliseconds = DEFAULT_TIMEOUT_IN_MILLISECONDS;
	
	//optional attribute
	private ILevel2Controller receiverController;
	
	//multiple attribute
	protected List<String> appendedCommands = new List<String>();
	
	//method
	/**
	 * Appends the given command to this duplex controller.
	 * 
	 * @param command
	 * @throws Exception if:
	 *  -the given command is null
	 *  -this duplex controller is stopped
	 */
	public final void appendCommand(String command) {
			
		throwExceptionIfStopped();
		
		appendedCommands.addAtEnd(command);
	}
	
	//method
	/**
	 * @return the receiver controller of this duplex controller
	 * @throws Exception if this duplex controller has no receiver controller
	 */
	public final ILevel2Controller getRefReceiverController() {
		
		if (!hasReceiverController()) {
			throw new UnexistingAttributeException(this, "receiver controller");
		}
		
		return receiverController;
	}
	
	//method
	/**
	 * @return the timeout of this duplex controller in milliseconds
	 */
	public final int getTimeoutInMilliseconds() {
		return timeoutInMilliseconds;
	}
	
	//method
	/**
	 * @return true if this duplex controller has a receiver controller
	 */
	public final boolean hasReceiverController() {
		return (receiverController != null);
	}
	
	//method
	/**
	 * @return true if this diplex controller is a local duplex controller
	 */
	public final boolean isLocalDuplexController() {
		return !isNetDuplexController();
	}
	
	//method
	/**
	 * @return true if this duplex controller is a net duplex controller
	 */
	public abstract boolean isNetDuplexController();
	
	//abstract method
	/**
	 * Runs and clears the appended comands of this duplex controller.
	 */
	public abstract void runAppendedCommands();
	
	//method
	/**
	 * Sets the receiver controller of this duplex controller.
	 * 
	 * @param receiverController
	 * @throws Exception if the given receiver controler is null
	 */
	public final void setReceiverController(ILevel2Controller receiverController) {
		
		Validator.throwExceptionIfValueIsNull("receiver controller", receiverController);
		
		this.receiverController = receiverController;
	}
	
	//method
	/**
	 * Sets the timeout of this duplex controller in milliseconds.
	 * 
	 * @param timeoutInMilliseconds
	 * @throws Exception if:
	 *  -this duplex controller is stopped
	 *  -the given timeout is not positive
	 */
	public final void setTimeoutInMilliseconds(int timeoutInMilliseconds) {
		
		throwExceptionIfStopped();
		
		Validator.throwExceptionIfValueIsNotPositive("timeout", timeoutInMilliseconds);

		this.timeoutInMilliseconds = timeoutInMilliseconds;
	}
	
	//abstract method
	/**
	 * Lets this duplex controller wait to the data the given request requests.
	 * 
	 * @param request
	 * @return the data the given request requests.
	 */
	public abstract Specification waitToData(String request);
	
	//method
	/**
	 * @throws Exception if this duplex controller is stopped
	 */
	protected final void throwExceptionIfStopped() {
		if (isAborted()) {
			throw new RuntimeException("Duplex controller is stopped.");
		}
	}
}
