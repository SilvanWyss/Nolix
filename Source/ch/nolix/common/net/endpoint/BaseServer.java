//package declaration
package ch.nolix.common.net.endpoint;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.programcontrol.closeableelement.CloseController;
import ch.nolix.common.programcontrol.closeableelement.ICloseableElement;
import ch.nolix.common.skillapi.Clearable;

//class
/**
 * A {@link BaseServer} contains {@link IEndPointTaker}s.
 * 
 * @author Silvan Wyss
 * @date 2017-05-06
 * @lines 220
 */
public abstract class BaseServer implements Clearable, ICloseableElement {
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//optional attribute
	private IEndPointTaker defaultEndPointTaker;
	
	//multi-attribute
	private final LinkedList<IEndPointTaker> endPointTakers = new LinkedList<>();
	
	//method
	/**
	 * Adds the given defaultEndPointTaker to the current {@link BaseServer}.
	 * A default {@link IEndPointTaker} takes all {@link EndPoint}s that do not have a target.
	 * 
	 * @param defaultEndPointTaker
	 * @throws InvalidArgumentException if the current {@link BaseServer} contains already
	 * a default {@link IEndPointTaker}.
	 * @throws InvalidArgumentException if the current {@link BaseServer} contains already
	 * a {@link IEndPointTaker} with the same name as the given endPointTaker.
	 */
	public final void addDefaultEndPointTaker(final IEndPointTaker defaultEndPointTaker) {
		
		//Asserts that the current Server does not contain already a default IEndPointTaker.
		assertDoesNotContainDefaultEndPointTaker();
		
		//Adds the given defaultEndPointTaker to the current Server.
		addEndPointTaker(defaultEndPointTaker);
		this.defaultEndPointTaker = defaultEndPointTaker;
	}
	
	//method
	/**
	 * Adds the given endPointTaker to the current {@link BaseServer}.
	 * 
	 * @param endPointTaker
	 * @throws InvalidArgumentException if the current {@link BaseServer} contains already
	 * a {@link IEndPointTaker} with the same name as the given endPointTaker.
	 */
	public final void addEndPointTaker(final IEndPointTaker endPointTaker) {
		
		//Extracts the name of the given endPointTaker.
		final var name = endPointTaker.getName();
		
		//Asserts that the current Server does not contain already
		//an IEndPointTaker with the same name as the given endPointTaker.
		assertDoesNotContainEndPointTakerWithName(name);
		
		//Adds the given endPointTaker to the current Server.
		this.endPointTakers.addAtEnd(endPointTaker);
	}
	
	//method
	/**
	 * Removes all {@link IEndPointTaker}s from the current {@link BaseServer}.
	 */
	@Override
	public final void clear() {
		endPointTakers.clear();
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
		return endPointTakers.contains(ept -> ept.hasName(name));
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
		return endPointTakers.isEmpty();
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
		removeEndPointTaker(endPointTakers.getRefFirst(ept -> ept.hasName(name)));
	}
	
	//method
	/**
	 * Lets the current {@link BaseServer} take the given endPoint.
	 * 
	 * @param endPoint
	 * @throws ClosedArgumentException if the given endPoint is closed.
	 * @throws ArgumentDoesNotHaveAttributeException if the given endPoint does not have a target and
	 * the current {@link BaseServer} does not have a default {@link IEndPointTaker}.
	 * @throws ArgumentDoesNotHaveAttributeException if the given endPoint has a target and
	 * the current {@link BaseServer} does not contain
	 * a {@link IEndPointTaker} with a name that equals the target of the given endPoint. 
	 */
	public final void takeEndPoint(final EndPoint endPoint) {
		
		//Asserts that the given endPoint is open.
		if (endPoint.isClosed()) {
			throw new ClosedArgumentException(endPoint);
		}
		
		//Handles the case that the given endPoint does not have a target.
		if (!endPoint.hasTarget()) {
			getRefDefaultEndPointTaker().takeEndPoint(endPoint);
		
		//Handles the case that the given endPoint has a target.
		} else {
			endPointTakers.getRefFirst(ept -> ept.hasName(endPoint.getTarget())).takeEndPoint(endPoint);
		}
	}
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BaseServer} does not contain
	 * a default {@link IEndPointTaker}.
	 */
	private void assertContainsDefaultEndPointTakter() {
		if (!containsDefaultEndPointTaker()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "default end point taker");
		}
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if the current {@link BaseServer} contains already
	 * a default {@link IEndPointTaker}.
	 */
	private void assertDoesNotContainDefaultEndPointTaker() {
		if (containsDefaultEndPointTaker()) {
			throw new InvalidArgumentException(this, "contains already a default end point taker");
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
			new InvalidArgumentException(this, "contains already an EndPointTaker with the name '" + name + "'");
		}
	}
	
	//method
	/**
	 * @return the default {@link IEndPointTaker} of the current {@link BaseServer}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BaseServer} does not contain
	 * a default {@link IEndPointTaker}.
	 */
	private IEndPointTaker getRefDefaultEndPointTaker() {
		
		assertContainsDefaultEndPointTakter();
		
		return defaultEndPointTaker;
	}
	
	//method
	/**
	 * Removes the given endPointTaker from the current {@link BaseServer}.
	 * 
	 * @param endPointTaker
	 * @throws InvalidArgumentException if the current {@link BaseServer} does not contain the given endPointTaker.
	 */
	private void removeEndPointTaker(final IEndPointTaker endPointTaker) {
		
		endPointTakers.removeFirst(endPointTaker);
		
		if (endPointTaker == defaultEndPointTaker) {
			defaultEndPointTaker = null;
		}
	}
}
