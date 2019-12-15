//package declaration
package ch.nolix.common.endPoint5;

//own imports
import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.common.closableElement.ClosableElement;
import ch.nolix.common.containers.List;
import ch.nolix.common.controllerAPI.IDataProviderController;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.validator.Validator;

//class
/**
 * A duplex controller can interact with another duplex controller of the same type.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 170
 */
public abstract class EndPoint extends ClosableElement implements IDataProviderController {
	
	//optional attribute
	private IDataProviderController receiverController;
	
	//multi-attribute
	private final List<ChainedNode> appendedCommands = new List<>();
	
	//method
	/**
	 * Appends the given command to this duplex controller.
	 * 
	 * @param command
	 * @throws ArgumentIsNullException if the given command is null.
	 * @throws InvalidArgumentException if this duplex controller is aborted.
	 */
	public final void appendCommand(final ChainedNode command) {
		
		supposeIsAlive();
		
		appendedCommands.addAtEnd(command);
	}
	
	//method
	/**
	 * Appends the given commands to this duplex controller.
	 * 
	 * @param commands
	 * @throws ArgumentIsNullException if one of the given commands is null.
	 * @throws InvalidArgumentException if this duplex controller is aborted.
	 */
	public final void appendCommand(final ChainedNode... commands) {
		
		supposeIsAlive();
		
		appendedCommands.addAtEnd(commands);
	}
	
	//method declaration
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
	
	//method declaration
	/**
	 * @return true if this duplex controller has requested the connection.
	 */
	public abstract boolean hasRequestedConnection();
	
	//method declaration
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
		
		//Handles the case that this duplex controller does not have a target.
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
	
	//method declaration
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
	 * @throws InvalidArgumentException if this local duplex controller is aborted.
	 */
	public final void runAppendedCommands()
	{
		//Checks if this local duplex controller is not aborted.
		supposeIsAlive();
		
		final var appendedCommands = this.appendedCommands.getCopy();
		this.appendedCommands.clear();
		run(appendedCommands);
	}
	
	//method
	/**
	 * Sets the receiver controller of this duplex controller.
	 * 
	 * @param receiverController
	 * @throws ArgumentIsNullException if the given receiver controller is null.
	 */
	public final void setReceiverController(final IDataProviderController receiverController) {
		
		//Checks if the given receiver controller is not null.
		Validator.suppose(receiverController).thatIsNamed("receiver controller").isNotNull();
		
		//Sets the receiver controller of this duplex controller.
		this.receiverController = receiverController;
	}
	
	//method
	/**
	 * @return the receiver controller of this duplex controller.
	 * @throws ArgumentDoesNotHaveAttributeException if this duplex controller does not have a receiver controller.
	 */
	protected IDataProviderController getRefReceiverController() {
		
		//Checks if this duplex controller has a receiver controller.
		if (!hasReceiverController()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "receiver controller");
		}
		
		return receiverController;
	}
	
	//method
	/**
	 * Lets this duplex controller note an abort.
	 */
	@Override
	protected final void noteClose() {}
	
	//method declaration
	/**
	 * Lets this duplex controller run the given commands.
	 * 
	 * @param commands
	 */
	protected abstract void run(List<ChainedNode> commands);
}
