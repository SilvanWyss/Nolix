//package declaration
package ch.nolix.core.net.endpoint3;

//own imports
import ch.nolix.core.document.chainednode.ChainedNode;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.controlleruniversalapi.IDataProviderController;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;

//class
/**
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public abstract class EndPoint implements GroupCloseable, IDataProviderController {
	
	//constant
	private static final int CONNECT_TIMEOUT_IN_MILLISECONDS = 500;
	
	//attribute
	private final CloseController closeController = CloseController.forElement(this);
	
	//optional attribute
	private IDataProviderController receiverController;
	
	//constructor
	EndPoint() {}
	
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
	public abstract String getCustomTargetSlot();
	
	//method
	/**
	 * @return true if the current {@link EndPoint} has a receiver controller
	 */
	public final boolean hasReceivingDataProviderController() {
		return (receiverController != null);
	}
	
	//method declaration
	/**
	 * @return true if the current {@link EndPoint} has requested the connection.
	 */
	public abstract boolean isFrontendEndPoint();
	
	//method declaration
	/**
	 * @return true if the current {@link EndPoint} has a target.
	 */
	public abstract boolean hasCustomTargetSlot();
	
	//method
	/**
	 * @return true if the current {@link EndPoint} is a local {@link EndPoint}.
	 */
	public final boolean isLocalEndPoint() {
		return !isSocketEndPoint();
	}
	
	//method declaration
	/**
	 * @return true if the current {@link EndPoint} is a net {@link EndPoint}.
	 */
	public abstract boolean isSocketEndPoint();
	
	//method declaration
	/**
	 * @return true if the current {@link EndPoint} is a web {@link EndPoint}.
	 */
	public abstract boolean isWebSocketEndPoint();
	
	//method declaration
	/**
	 * Lets current {@link EndPoint} run the given commands.
	 * 
	 * @param commands
	 */
	public abstract void run(Iterable<ChainedNode> commands);
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void run(final ChainedNode command, ChainedNode... commands) {
		
		run(command);
		
		//Iterates the given commands.
		for (final var c : commands) {
			run(c);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteClose() {}
	
	//method
	/**
	 * Sets the receiver controller of the current {@link EndPoint}.
	 * 
	 * @param receiverController
	 * @throws ArgumentIsNullException if the given receiverController is null.
	 */
	public final void setReceivingDataProviderController(final IDataProviderController receiverController) {
		
		//Asserts that the given receiverController is not null.
		GlobalValidator.assertThat(receiverController).thatIsNamed("receiver controller").isNotNull();
		
		//Sets the receiver controller of the current EndPoint.
		this.receiverController = receiverController;
	}
	
	//method
	/**
	 * @throws ClosedArgumentException if the current {@link EndPoint} is closed.
	 */
	protected void assertIsOpen() {
		if (isClosed()) {
			throw ClosedArgumentException.forArgument(this);
		}
	}
	
	//method
	/**
	 * @return the receiver controller of the current {@link EndPoint}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link EndPoint} does not have a receiver controller.
	 */
	IDataProviderController getRefReceiverController() {
		
		if (hasReceivingDataProviderController()) {
			return receiverController;
		}
		
		GlobalSequencer.forMaxMilliseconds(CONNECT_TIMEOUT_IN_MILLISECONDS).waitUntil(this::hasReceivingDataProviderController);
		
		if (!hasReceivingDataProviderController()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.RECEIVER);
		}
		
		return receiverController;
	}
}
