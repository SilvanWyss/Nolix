//package declaration
package ch.nolix.core.duplexController;

//own imports
import ch.nolix.core.bases.ClosableElement;
import ch.nolix.core.container.List;
import ch.nolix.core.controllerInterfaces.IMultiController;
import ch.nolix.core.invalidStateException.InvalidStateException;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.specification.Statement;
import ch.nolix.core.validator2.Validator;

//class
/**
 * A duplex controller can interact with another duplex controller of the same type.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 200
 */
public abstract class DuplexController
extends ClosableElement
implements IMultiController {	
	
	//optional attribute
	private IMultiController receiverController;
	
	//multiple attribute
	private final List<String> appendedCommands = new List<String>();
	
	//method
	/**
	 * Appends the given command to this duplex controller.
	 * 
	 * @param command
	 * @throws NullArgumentException if the given command is null.
	 * @throws InvalidStateException if this duplex controller is aborted.
	 */
	public final void appendCommand(final Statement command) {
		appendCommand(command.toString());
	}
	
	//method
	/**
	 * Appends the given commands to this duplex controller.
	 * 
	 * @param commands
	 * @throws NullArgumentException if one of the given commands is null.
	 * @throws InvalidStateException if this duplex controller is aborted.
	 */
	public final void appendCommand(final Statement... commands) {
		
		//Checks if this duplex controller is not aborted.
		supposeBeingAlive();
		
		appendedCommands.using(c -> c.toString()).addAtEnd(commands);
	}
	
	//method
	/**
	 * Appends the given command to this duplex controller.
	 * 
	 * @param command
	 * @throws NullArgumentException if the given command is null.
	 * @throws InvalidStateException if this duplex controller is aborted.
	 */
	public final void appendCommand(final String command) {

		//Checks if this duplex controller is not aborted.
		supposeBeingAlive();
		
		appendedCommands.addAtEnd(command);
	}
	
	//method
	/**
	 * Appends the given commands to this duplex controller.
	 * 
	 * @param commands
	 * @throws NullArgumentException if one of the given commands is null.
	 * @throws InvalidStateException if this duplex controller is aborted.
	 */
	public final void appendCommand(String... commands) {
		
		//Checks if this duplex controller is not aborted.
		supposeBeingAlive();
		
		appendedCommands.addAtEnd(commands);
	}
	
	//abstract method
	/**
	 * @return the target of this duplex controller.
	 */
	public abstract String getTarget();
	
	//method
	/**
	 * @return true if this duplex controller has a receiver controller
	 */
	public final boolean hasReceiverController() {
		return (receiverController != null);
	}
	
	//abstract method
	/**
	 * @return true if this duplex controller has requested the connection.
	 */
	public abstract boolean hasRequestedConnection();
	
	//abstract method
	/**
	 * @return true if this duplex controller has a target.
	 */
	public abstract boolean hasTarget();
	
	//method
	/**
	 * @param target
	 * @return true if this duplex controller has the given target.
	 */
	public final boolean hasTarget(final String target) {
		
		//Handles the case that this duplex controller has no target.
		if (!hasTarget()) {
			return false;
		}
		
		//Handles the case that this duplex controller has a target.
		return getTarget().equals(target);
	}
	
	//method
	/**
	 * @return true if this diplex controller is a local duplex controller.
	 */
	public final boolean isLocalDuplexController() {
		return !isNetDuplexController();
	}
	
	//abstract method
	/**
	 * @return true if this duplex controller is a net duplex controller.
	 */
	public abstract boolean isNetDuplexController();
	
	//method
	/**
	 * Runs and removes the appended commands of this duplex controller.
	 * This method allows that an appended command leads to further appended commands.
	 * The appended commands of this local duplex controller will be removed in any case.
	 * 
	 * @throws InvalidStateException if this local duplex controller is aborted.
	 */
	public final void runAppendedCommands()
	{
		//Checks if this local duplex controller is not aborted.
		supposeBeingAlive();
		
		final List<String> appendedCommands = this.appendedCommands.getCopy();
		this.appendedCommands.clear();
		run(appendedCommands);
	}
	
	//method
	/**
	 * Sets the receiver controller of this duplex controller.
	 * 
	 * @param receiverController
	 * @throws NullArgumentException if the given receiver controller is null.
	 */
	public final void setReceiverController(final IMultiController receiverController) {
		
		//Checks if the given receiver controller is not null.
		Validator.suppose(receiverController).thatIsNamed("receiver controller").isNotNull();
		
		//Sets the receiver controller of this duplex controller.
		this.receiverController = receiverController;
	}
	
	//method
	/**
	 * @return the receiver controller of this duplex controller.
	 * @throws UnexistingAttributeException if this duplex controller has no receiver controller.
	 */
	protected IMultiController getRefReceiverController() {
		
		//Checks if this duplex controller has a receiver controller.
		if (!hasReceiverController()) {
			throw new UnexistingAttributeException(this, "receiver controller");
		}
		
		return receiverController;
	}
	
	//method
	/**
	 * Lets this duplex controller note an abort.
	 */
	protected final void noteClosing() {}
	
	//abstract method
	/**
	 * Lets this duplex controller run the given commands.
	 * 
	 * @param commands
	 */
	protected abstract void run(List<String> commands);
}
