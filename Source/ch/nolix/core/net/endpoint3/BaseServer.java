//package declaration
package ch.nolix.core.net.endpoint3;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.coreapi.netapi.endpoint3api.IEndPoint;
import ch.nolix.coreapi.netapi.endpoint3api.IServer;
import ch.nolix.coreapi.netapi.endpoint3api.ISlot;

//class
/**
 * @author Silvan Wyss
 * @date 2017-06-16
 */
public abstract class BaseServer implements IServer {
	
	//attribute
	private final CloseController closeController = CloseController.forElement(this);
	
	//optional attribute
	private ISlot defaultSlot;
	
	//multi-attribute
	private final LinkedList<ISlot> slots = new LinkedList<>();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void addDefaultSlot(final ISlot defaultEndPointTaker) {
		
		addSlotToList(defaultEndPointTaker);
		this.defaultSlot = defaultEndPointTaker;
		
		noteAddedDefaultSlot(defaultEndPointTaker);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void addSlot(final ISlot endPointTaker) {
		
		addSlotToList(endPointTaker);
		
		noteAddedSlot(endPointTaker);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean containsDefaultSlot() {
		return (defaultSlot != null);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean containsSlotWithName(final String name) {
		return slots.containsAny(ept -> ept.hasName(name));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final CloseController getStoredCloseController() {
		return closeController;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteClose() {}
	
	//method declaration
	/**
	 * Notes that the current {@link BaseServer} has added the given defaultEndPointTaker.
	 * 
	 * @param defaultEndPointTaker
	 */
	protected abstract void noteAddedDefaultSlot(ISlot defaultEndPointTaker);
	
	//method declaration
	/**
	 * Notes that the current {@link BaseServer} has added the given endPointTaker.
	 * 
	 * @param endPointTaker
	 */
	protected abstract void noteAddedSlot(ISlot endPointTaker);
	
	//method
	/**
	 * Lets the current {@link BaseServer} take the given endPoint.
	 * 
	 * @param endPoint
	 * @throws ClosedArgumentException if the given endPoint is closed.
	 * @throws ArgumentDoesNotHaveAttributeException if the given endPoint does not have a target and
	 * the current {@link BaseServer} does not contain a default {@link IEndPointTaker}.
	 * @throws ArgumentDoesNotHaveAttributeException if the given endPoint has a target and
	 * the current {@link BaseServer} does not contain
	 * a {@link IEndPointTaker} with a name that equals the target of the given endPoint. 
	 */
	final void internalTakeBackendEndPoint(final IEndPoint endPoint) {
		
		//Asserts that the given endPoint is open.
		assertIsOpen(endPoint);
		
		//Handles the case that the given endPoint does not have a target.
		if (!endPoint.hasCustomTargetSlot()) {
			getStoredDefaultSlot().takeBackendEndPoint(endPoint);
		
		//Handles the case that the given endPoint has a target.
		} else {
			getStoredSlotByName(endPoint.getCustomTargetSlot()).takeBackendEndPoint(endPoint);
		}
	}

	//method
	/**
	 * Adds the given endPointTaker to the list of {@link IEndPointTaker}s of the current {@link BaseServer}.
	 * 
	 * @param endPointTaker
	 * @throws InvalidArgumentException if the current {@link BaseServer} contains already
	 * a {@link IEndPointTaker} with the same name as the given endPointTaker.
	 */
	private void addSlotToList(final ISlot endPointTaker) {
		
		//Extracts the name of the given endPointTaker.
		final var name = endPointTaker.getName();
		
		//Asserts that the current Server does not contain
		//an EndPointTaker with the same name as the given endPointTaker.
		assertDoesNotContainSlotWithName(name);
		
		//Adds the given endPointTaker to the current Server.
		this.slots.addAtEnd(endPointTaker);
	}
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BaseServer} does not contain
	 * a default {@link IEndPointTaker}.
	 */
	private void assertContainsDefaultSlot() {
		if (!containsDefaultSlot()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "default end point taker");
		}
	}
	
	//method
	/**
	 * @param name
	 * @throws InvalidArgumentException if the current {@link BaseServer} contains already
	 * a {@link IEndPointTaker} with the same name as the given endPointTaker.
	 */
	private void assertDoesNotContainSlotWithName(final String name) {
		if (containsSlotWithName(name)) {
			throw
			InvalidArgumentException.forArgumentAndErrorPredicate(
				this,"contains already an EndPointTaker with the name '" + name + "'"
			);
		}
	}
	
	//method
	/**
	 * @param endPoint
	 * @throws ClosedArgumentException if the given endPoint is closed.
	 */
	private void assertIsOpen(final IEndPoint endPoint) {
		if (endPoint.isClosed()) {
			throw ClosedArgumentException.forArgument(endPoint);
		}
	}
	
	//method
	/**
	 * @return the default {@link IEndPointTaker} of the current {@link BaseServer}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BaseServer} does not contain
	 * a default {@link IEndPointTaker}.
	 */
	private ISlot getStoredDefaultSlot() {
		
		assertContainsDefaultSlot();
		
		return defaultSlot;
	}
	
	//method
	/**
	 * 
	 * @param name
	 * @return the {@link IEndPointTaker} with the given name from the current {@link BaseServer}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BaseServer} does not contain
	 * a {@link IEndPointTaker} with the given name. 
	 */
	private ISlot getStoredSlotByName(final String name) {
		return slots.getStoredFirst(ept -> ept.hasName(name));
	}
}
