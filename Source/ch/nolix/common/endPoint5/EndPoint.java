//package declaration
package ch.nolix.common.endPoint5;

//own imports
import ch.nolix.common.chainedNode.ChainedNode;
import ch.nolix.common.closableElement.ClosableElement;
import ch.nolix.common.communicationAPI.IReceiver;
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.controllerAPI.IDataProviderController;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.nolixEnvironment.NolixEnvironment;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.validator.Validator;

//class
/**
 * A {@link EndPoint} can interact with another {@link EndPoint} of the same type.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 180
 */
public abstract class EndPoint extends ClosableElement implements IDataProviderController {
	
	//optional attribute
	private IDataProviderController receiverController;
	
	//multi-attribute
	private final LinkedList<ChainedNode> appendedCommands = new LinkedList<>();
	
	//access-reducing constructor
	EndPoint() {}
	
	//method
	/**
	 * Appends the given command to current {@link EndPoint}.
	 * 
	 * @param command
	 * @throws ArgumentIsNullException if the given command is null.
	 * @throws ClosedArgumentException if the current {@link EndPoint} is closed.
	 */
	public final void appendCommand(final ChainedNode command) {
		
		supposeIsAlive();
		
		appendedCommands.addAtEnd(command);
	}
	
	//method
	/**
	 * Appends the given commands to current {@link EndPoint}.
	 * 
	 * @param commands
	 * @throws ArgumentIsNullException if one of the given commands is null.
	 * @throws ClosedArgumentException if the current {@link EndPoint} is closed.
	 */
	public final void appendCommand(final ChainedNode... commands) {
		
		supposeIsAlive();
		
		appendedCommands.addAtEnd(commands);
	}
	
	//method declaration
	/**
	 * @return the target of the current {@link EndPoint}.
	 */
	public abstract String getTarget();
	
	//method
	/**
	 * @return true if the current {@link EndPoint} has a receiver controller
	 */
	public final boolean hasReceiverController() {
		return (receiverController != null);
	}
	
	//method declaration
	/**
	 * @return true if the current {@link EndPoint} has requested the connection.
	 */
	public abstract boolean hasRequestedConnection();
	
	//method declaration
	/**
	 * @return true if the current {@link EndPoint} has a target.
	 */
	public abstract boolean hasTarget();
	
	//method
	/**
	 * @param target
	 * @return true if the current {@link EndPoint} has the given target.
	 */
	public final boolean hasTarget(final String target) {
		
		//Handles the case that current EndPoint does not have a target.
		if (!hasTarget()) {
			return false;
		}
		
		//Handles the case that current EndPoint has a target.
		return getTarget().equals(target);
	}
	
	//method
	/**
	 * @return true if the current {@link EndPoint} is a local {@link EndPoint}.
	 */
	public final boolean isLocalEndPoint() {
		return !isNetEndPoint();
	}
	
	//method declaration
	/**
	 * @return true if the current {@link EndPoint} is a net {@link EndPoint}.
	 */
	public abstract boolean isNetEndPoint();
	
	//method
	/**
	 * Runs the appended commands of the current {@link EndPoint}.
	 * The appended commands of the current local {@link EndPoint} will be removed in any case.
	 * 
	 * @throws InvalidArgumentException if the current local {@link EndPoint} is closed.
	 */
	public final void runAppendedCommands() {
		
		supposeIsAlive();
		
		final var appendedCommands = this.appendedCommands.getCopy();
		this.appendedCommands.clear();
		run(appendedCommands);
	}
	
	//method
	/**
	 * Sets the receiver controller of the current {@link EndPoint}.
	 * 
	 * @param receiverController
	 * @throws ArgumentIsNullException if the given receiverController is null.
	 */
	public final void setReceiverController(final IDataProviderController receiverController) {
		
		//Checks if the given receiverController is not null.
		Validator.suppose(receiverController).thatIsNamed("receiver controller").isNotNull();
		
		//Sets the receiver controller of the current EndPoint.
		this.receiverController = receiverController;
	}
	
	//method
	/**
	 * {@inheritDoc)
	 */
	@Override
	protected final void noteClose() {}
	
	//method
	/**
	 * @return the receiver controller of the current {@link EndPoint}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link EndPoint} does not have a receiver controller.
	 */
	IDataProviderController getRefReceiverController() {
		
		if (hasReceiverController()) {
			return receiverController;
		}
		
		Sequencer
		.forMaxMilliseconds(NolixEnvironment.DEFAULT_CONNECT_AND_DISCONNECT_TIMEOUT_IN_MILLISECONDS)
		.waitUntil(() -> hasReceiverController());
		
		if (!hasReceiverController()) {
			throw new ArgumentDoesNotHaveAttributeException(this, IReceiver.class);
		}
		
		return receiverController;
	}
	
	//method declaration
	/**
	 * Lets current {@link EndPoint} run the given commands.
	 * 
	 * @param commands
	 */
	abstract void run(LinkedList<ChainedNode> commands);
}
