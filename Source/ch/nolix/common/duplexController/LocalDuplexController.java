/*
 * file:	LocalBidirectionalController.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	210
 */

//package declaration
package ch.nolix.common.duplexController;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.controller.ILevel2Controller;
import ch.nolix.common.exception.UnexistingPropertyException;
import ch.nolix.common.specification.Statement;
import ch.nolix.common.util.Validator;

//class
public final class LocalDuplexController extends DuplexController {
	
	//attribute
	private boolean stopped = false;
	
	//optional attributes
	private LocalDuplexController targetController;
	private String stopReason;
	
	//constructor
	/**
	 * Creates new local duplex controller with default values.
	 */
	public LocalDuplexController() {}
	
	//constructor
	/**
	 * Creates new local duplex controller with the given receiver controller.
	 * 
	 * @param receiverController
	 * @throws Exception if the given receiver controller is null
	 */
	/*
	public LocalDuplexController(final ILevel2Controller receiverController) {
		setReceiverController(receiverController);
	}
	*/
	
	/*
	public LocalDuplexController(final LocalDuplexController localDuplexController) {
		connectWith(localDuplexController);
	}
	*/
	
	//method
	/**
	 * Connects this local duplex controller with the given target controller.
	 * 
	 * @param localBidirectionalController
	 * @return this local duplex controller
	 * @throws Exception if the given target controller is null
	 */
	public final void connectWith(LocalDuplexController targetController) {
		
		Validator.throwExceptionIfValueIsNull("local duplex controller", targetController);
		
		this.targetController = targetController;
		this.targetController.targetController = this;
	}
	
	//method
	/**
	 * @param request
	 * @return the data the given request requests
	 * @throws Exception if:
	 *  -this local duplex controller is stopped
	 *  -this local duplex controller has no target controller
	 *  -the target controller of this local duplex controller has no receiver controller
	 *  -the given request is not valid
	 */
	public final Object getRawData(Statement request) {
		
		throwExceptionIfStopped();

		return getRefTargetController().getRefReceiverController().getData(request);
	}
	
	//method
	/**
	 * @return the stop reason of this local duplex controller
	 * @throws Exception if:
	 *  -this local duplex controller is not stopped
	 *  -this local duplex controller has no stop reason
	 */
	public final String getAbortReason() {
		
		if (!isAborted()) {
			throw new RuntimeException("Local duplex controller is not stopped.");
		}
		
		if (!hasStopReason()) {
			throw new UnexistingPropertyException(this, "strop reason");
		}
		
		return stopReason;
	}
	
	//method
	/**
	 * @return true if this local duplex controller has a target controller
	 */
	public final boolean hasTargetController() {
		return (targetController != null);
	}

	//method
	/**
	 * @return true if this duplex controller is a net duplex controller
	 */
	public boolean isNetDuplexController() {
		return false;
	}
	
	//method
	/**
	 * @return true if this local duplex controller is stopped
	 */
	public final boolean isAborted() {
		return stopped;
	}
	
	//method
	/**
	 * Run and clears the appended commands of this local duplex controller.
	 * This method always clears the appended commands of this local duplex controller.
	 * 
	 * @throws Exception if:
	 *  -this local duplex controller is stopped
	 *  -this local duplex controller has no target duplex controller
	 *  -one of the appended commands of this local duplex controller is not valid
	 */
	public final void runAppendedCommands() {
		try {
			
			throwExceptionIfStopped();
			
			List<String> appendedCommands = this.appendedCommands.getCopy();
			this.appendedCommands = new List<String>();
			ILevel2Controller targetControllerReceiverController = getRefTargetController().getRefReceiverController();
			appendedCommands.forEach(ac -> targetControllerReceiverController.run(ac));
		}
		finally {
			appendedCommands.clear();
		}		
	}
	
	//method
	/**
	 * Runs the given command.
	 * 
	 * @param command
	 * @throws Exception if
	 *  -this local duplex controller is stopped
	 *  -this local duplex controller has no target controller
	 *  -the given command is not valid
	 */
	public final void run(Statement command) {
		
		throwExceptionIfStopped();
		
		getRefTargetController().getRefReceiverController().run(command);
	}
	
	//method
	/**
	 * Stops this local duplex controller.
	 * 
	 * @throws Exception if this local duplex controller is stopped already
	 */
	public final void abort() {
		
		throwExceptionIfStopped();
		
		stopped = true;
	}
	
	//method
	/**
	 * Stops this local duplex controller because of the given stop reason.
	 * 
	 * @param stopReason
	 * @throws Exception if:
	 *  -the given stop reason is null or an empty string
	 * 	-this local duplex controller is stopped already
	 */
	public final void abort(String stopReason) {
		
		Validator.throwExceptionIfStringIsNullOrEmpty(stopReason, "stop reason");	
		
		abort();
		this.stopReason = stopReason;
	}
	
	//method
	/**
	 * @return the target controller of this local duplex controller
	 * @throws Exception if this duplex controller has no target controller
	 */
	private final DuplexController getRefTargetController() {
		
		if (!hasTargetController()) {
			throw new UnexistingPropertyException(this, "target controller");
		}
		
		return targetController;
	}
	
	//method
	/**
	 * @return true if this local duplex controller has a stop reason
	 */
	private final boolean hasStopReason() {
		return (stopReason != null);
	}
}
