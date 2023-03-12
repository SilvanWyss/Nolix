//package declaration
package ch.nolix.core.net.endpoint;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.coreapi.functionapi.mutationuniversalapi.Clearable;
import ch.nolix.coreapi.netapi.endpointapi.ISlot;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;

//class
/**
 * @author Silvan Wyss
 * @date 2017-05-06
 */
public abstract class BaseServer implements Clearable, GroupCloseable {
	
	//attribute
	private final CloseController closeController = CloseController.forElement(this);
	
	//optional attribute
	private ISlot defaultEndPointTaker;
	
	//multi-attribute
	private final LinkedList<ISlot> slots = new LinkedList<>();
	
	//method
	/**
	 * Adds the given defaultEndPointTaker to the current {@link BaseServer}.
	 * A default {@link IEndPointTaker} takes all {@link EndPoint}s that do not have a target.
	 * 
	 * @param defaultSlot
	 * @throws InvalidArgumentException if the current {@link BaseServer} contains already
	 * a {@link IEndPointTaker} with the same name as the given endPointTaker.
	 */
	public final void addDefaultSlot(final ISlot defaultSlot) {
		addSlot(defaultSlot);
		this.defaultEndPointTaker = defaultSlot;
	}
	
	//method
	/**
	 * Adds the given endPointTaker to the current {@link BaseServer}.
	 * 
	 * @param slot
	 * @throws InvalidArgumentException if the current {@link BaseServer} contains already
	 * a {@link IEndPointTaker} with the same name as the given endPointTaker.
	 */
	public final void addSlot(final ISlot slot) {
		
		//Extracts the name of the given endPointTaker.
		final var name = slot.getName();
		
		//Asserts that the current Server does not contain already
		//an IEndPointTaker with the same name as the given endPointTaker.
		assertDoesNotContainEndPointTakerWithName(name);
		
		//Adds the given endPointTaker to the current Server.
		this.slots.addAtEnd(slot);
	}
	
	//method
	/**
	 * Removes all {@link IEndPointTaker}s from the current {@link BaseServer}.
	 */
	@Override
	public final void clear() {
		slots.clear();
		defaultEndPointTaker = null;
	}
	
	//method
	/**
	 * @return true if the current {@link BaseServer} contains a default {@link IEndPointTaker}.
	 */
	public final boolean containsDefaultEndPointTaker() {
		return (defaultEndPointTaker != null);
	}
	
	//method
	/**
	 * @param name
	 * @return true if the current {@link BaseServer} contains a {@link IEndPointTaker} with the given name.
	 */
	public final boolean containsEndPointTakerWithName(final String name) {
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
	 * @return true if the current {@link BaseServer} does not contain a {@link IEndPointTaker}.
	 */
	@Override
	public final boolean isEmpty() {
		return slots.isEmpty();
	}
	
	//method
	/**
	 * Removes the {@link IEndPointTaker} with the given name from the current {@link BaseServer}.
	 * 
	 * @param name
	 * @throws InvalidArgumentException if the current {@link BaseServer} does not contain
	 * a {@link IEndPointTaker} with the given name.
	 */
	public final void removeEndPointTakerWithName(final String name) {
		removeEndPointTaker(slots.getRefFirst(ept -> ept.hasName(name)));
	}
	
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
	public final void takeEndPoint(final EndPoint endPoint) {
		
		//Asserts that the given endPoint is open.
		endPoint.assertIsOpen();
		
		//Handles the case that the given endPoint does not have a target.
		if (!endPoint.hasCustomTargetSlot()) {
			getRefDefaultEndPointTaker().takeBackendEndPoint(endPoint);
		
		//Handles the case that the given endPoint has a target.
		} else {
			getRefEndPointTakerByName(endPoint.getCustomTargetSlot()).takeBackendEndPoint(endPoint);
		}
	}
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BaseServer} does not contain
	 * a default {@link IEndPointTaker}.
	 */
	private void assertContainsDefaultEndPointTakter() {
		if (!containsDefaultEndPointTaker()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "default end point taker");
		}
	}
	
	//method
	/**
	 * @param name
	 * @throws InvalidArgumentException if the current {@link BaseServer} contains already
	 * a {@link IEndPointTaker} with the same name as the given endPointTaker.
	 */
	private void assertDoesNotContainEndPointTakerWithName(final String name) {
		if (containsEndPointTakerWithName(name)) {
			throw
			InvalidArgumentException.forArgumentAndErrorPredicate(
				this,
				"contains already an EndPointTaker with the name '" + name + "'"
			);
		}
	}
	
	//method
	/**
	 * @return the default {@link IEndPointTaker} of the current {@link BaseServer}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BaseServer} does not contain
	 * a default {@link IEndPointTaker}.
	 */
	private ISlot getRefDefaultEndPointTaker() {
		
		assertContainsDefaultEndPointTakter();
		
		return defaultEndPointTaker;
	}
	
	//method
	/**
	 * 
	 * @param name
	 * @return the {@link IEndPointTaker} with the given name from the current {@link BaseServer}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BaseServer} does not contain
	 * a {@link IEndPointTaker} with the given name. 
	 */
	private ISlot getRefEndPointTakerByName(final String name) {
		return slots.getRefFirst(ept -> ept.hasName(name));
	}
	
	//method
	/**
	 * Removes the given endPointTaker from the current {@link BaseServer}.
	 * 
	 * @param endPointTaker
	 * @throws InvalidArgumentException if the current {@link BaseServer} does not contain the given endPointTaker.
	 */
	private void removeEndPointTaker(final ISlot endPointTaker) {
		
		slots.removeFirstOccurrenceOf(endPointTaker);
		
		if (endPointTaker == defaultEndPointTaker) {
			defaultEndPointTaker = null;
		}
	}
}
