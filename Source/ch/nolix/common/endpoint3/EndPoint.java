//package declaration
package ch.nolix.common.endpoint3;

//own imports
import ch.nolix.common.chainednode.ChainedNode;
import ch.nolix.common.closeableelement.CloseController;
import ch.nolix.common.closeableelement.ICloseableElement;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.controllerapi.IDataProviderController;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.nolixenvironment.NolixEnvironment;
import ch.nolix.common.sequencer.Sequencer;

//class
/**
 * A {@link EndPoint} can interact with another {@link EndPoint} of the same type.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 200
 */
public abstract class EndPoint implements ICloseableElement, IDataProviderController {
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//optional attribute
	private IDataProviderController receiverController;
	
	//multi-attribute
	private final LinkedList<ChainedNode> appendedCommands = new LinkedList<>();
	
	//visibility-reduced constructor
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
		
		assertIsOpen();
		
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
		
		assertIsOpen();
		
		appendedCommands.addAtEnd(commands);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final CloseController getRefCloseController() {
		return closeController;
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
	
	//method declaration
	/**
	 * @return true if the current {@link EndPoint} is a web {@link EndPoint}.
	 */
	public abstract boolean isWebEndPoint();
	
	//method declaration
	/**
	 * Lets current {@link EndPoint} run the given commands.
	 * 
	 * @param commands
	 */
	public abstract void run(Iterable<ChainedNode> commands);
	
	//method
	/**
	 * Runs the appended commands of the current {@link EndPoint}.
	 * The appended commands of the current local {@link EndPoint} will be removed in any case.
	 * 
	 * @throws ClosedArgumentException if the current local {@link EndPoint} is closed.
	 */
	public final void runAppendedCommands() {
		
		assertIsOpen();
		
		final var lAppendedCommands = appendedCommands.getCopy();
		appendedCommands.clear();
		run(lAppendedCommands);
	}
	
	//method
	/**
	 * Sets the receiver controller of the current {@link EndPoint}.
	 * 
	 * @param receiverController
	 * @throws ArgumentIsNullException if the given receiverController is null.
	 */
	public final void setReceiverController(final IDataProviderController receiverController) {
		
		//Asserts that the given receiverController is not null.
		Validator.assertThat(receiverController).thatIsNamed("receiver controller").isNotNull();
		
		//Sets the receiver controller of the current EndPoint.
		this.receiverController = receiverController;
	}
	
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
		.waitUntil(this::hasReceiverController);
		
		if (!hasReceiverController()) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.RECEIVER);
		}
		
		return receiverController;
	}
}
