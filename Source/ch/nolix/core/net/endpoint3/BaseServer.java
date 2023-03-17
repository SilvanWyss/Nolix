//package declaration
package ch.nolix.core.net.endpoint3;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;

//class
/**
 * @author Silvan Wyss
 * @date 2017-06-16
 */
public abstract class BaseServer implements GroupCloseable {
	
	//attribute
	private final CloseController closeController = CloseController.forElement(this);
	
	//optional attribute
	private IEndPointTaker defaultSlot;
	
	//multi-attribute
	private final LinkedList<IEndPointTaker> slots = new LinkedList<>();
	
	//method
	/**
	 * Adds the given defaultEndPointTaker to the current {@link BaseServer}.
	 * A default {@link IEndPointTaker} takes all {@link EndPoint}s that do not have a target.
	 * 
	 * @param defaultEndPointTaker
	 * @throws InvalidArgumentException if the current {@link BaseServer} contains already
	 * a {@link IEndPointTaker} with the same name as the given defaultEndPointTaker.
	 */
	public final void addDefaultSlot(final IEndPointTaker defaultEndPointTaker) {
		
		addSlotToList(defaultEndPointTaker);
		this.defaultSlot = defaultEndPointTaker;
		
		noteAddedDefaultSlot(defaultEndPointTaker);
	}
	
	//method
	/**
	 * Adds the given endPointTaker to the current {@link BaseServer}.
	 * 
	 * @param endPointTaker
	 * @throws InvalidArgumentException if the current {@link BaseServer} contains already
	 * a {@link IEndPointTaker} with the same name as the given endPointTaker.
	 */
	public final void addSlot(final IEndPointTaker endPointTaker) {
		
		addSlotToList(endPointTaker);
		
		noteAddedSlot(endPointTaker);
	}
	
	//method
	/**
	 * @return true if the current {@link BaseServer} contains a default {@link IEndPointTaker}.
	 */
	public final boolean containsDefaultSlot() {
		return (defaultSlot != null);
	}
	
	//method
	/**
	 * @param name
	 * @return true if the current {@link BaseServer} contains a {@link IEndPointTaker} with the given name.
	 */
	public final boolean containsSlotWithName(final String name) {
		return slots.containsAny(ept -> ept.hasName(name));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final CloseController getRefCloseController() {
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
	protected abstract void noteAddedDefaultSlot(IEndPointTaker defaultEndPointTaker);
	
	//method declaration
	/**
	 * Notes that the current {@link BaseServer} has added the given endPointTaker.
	 * 
	 * @param endPointTaker
	 */
	protected abstract void noteAddedSlot(IEndPointTaker endPointTaker);
	
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
	final void internalTakeBackendEndPoint(final EndPoint endPoint) {
		
		//Asserts that the given endPoint is open.
		endPoint.assertIsOpen();
		
		//Handles the case that the given endPoint does not have a target.
		if (!endPoint.hasCustomTargetSlot()) {
			getRefDefaultSlot().takeEndPoint(endPoint);
		
		//Handles the case that the given endPoint has a target.
		} else {
			getRefSlotByName(endPoint.getCustomTargetSlot()).takeEndPoint(endPoint);
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
	private void addSlotToList(IEndPointTaker endPointTaker) {
		
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
	 * @return the default {@link IEndPointTaker} of the current {@link BaseServer}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BaseServer} does not contain
	 * a default {@link IEndPointTaker}.
	 */
	private IEndPointTaker getRefDefaultSlot() {
		
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
	private IEndPointTaker getRefSlotByName(final String name) {
		return slots.getRefFirst(ept -> ept.hasName(name));
	}
}
