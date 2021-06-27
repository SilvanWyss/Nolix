//package declaration
package ch.nolix.common.net.endpoint2;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.programcontrol.closeableelement.CloseController;
import ch.nolix.common.programcontrol.closeableelement.ICloseableElement;
import ch.nolix.common.skillapi.Clearable;

//class
/**
 * A {@link BaseServer} contains {@link IEndPointTaker}s.
 * A {@link BaseServer} is clearable and closable.
 * 
 * @author Silvan Wyss
 * @date 2017-03-05
 * @lines 180
 */
public abstract class BaseServer implements Clearable, ICloseableElement {
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//optional attribute
	private IEndPointTaker mainEndPointTaker;
	
	//multi-attribute
	private final LinkedList<IEndPointTaker> endPointTakers = new LinkedList<>();
	
	//method
	/**
	 * Adds the given endPointTaker to the current {@link BaseServer}.
	 * 
	 * @param endPointTaker
	 * @throws InvalidArgumentException if the current {@link BaseServer} contains already a {@link IEndPointTaker}
	 * with the same name as the given endPointTaker.
	 */
	public final void addEndPointTaker(final IEndPointTaker endPointTaker) {
		
		//Extracts the name of the given endPointTaker.
		final var name = endPointTaker.getName();
		
		//Asserts that the current Server
		//contains already an IEndPointTaker with the same name as the given endPointTaker.
		if (containsEndPointTaker(name)) {
			throw
			new InvalidArgumentException(this, "contains another EndPointTaker with the name '" + name + "'");
		}
		
		this.endPointTakers.addAtEnd(endPointTaker);
	}
	
	//method
	/**
	 * Adds the given mainEndPointTaker to the current {@link BaseServer}.
	 * A main {@link IEndPointTaker} takes all {@link EndPoint}s without target.
	 * 
	 * @param mainEndPointTaker
	 * @throws InvalidArgumentException if the current {@link BaseServer} contains already a main {@link IEndPointTaker}.
	 */
	public final void addMainEndPointTaker(final IEndPointTaker mainEndPointTaker) {
		
		//Asserts that the current Server does not contain already a main IEndPointTaker.
		if (containsMainEndPointTaker()) {
			throw new InvalidArgumentException(this, "contains already a mainEndPointTaker");
		}
		
		addEndPointTaker(mainEndPointTaker);
		
		this.mainEndPointTaker = mainEndPointTaker;
	}
	
	//method
	/**
	 * Removes all {@link IEndPointTaker}s from the current {@link BaseServer}.
	 */
	@Override
	public final void clear() {
		endPointTakers.clear();
		mainEndPointTaker = null;
	}
	
	//method
	/**
	 * @param name
	 * @return true if the current {@link BaseServer} contains a {@link IEndPointTaker} with the given name.
	 */
	public final boolean containsEndPointTaker(final String name) {
		return endPointTakers.contains(ept -> ept.hasName(name));
	}
	
	//method
	/**
	 * @return true if the current {@link BaseServer} contains a main {@link IEndPointTaker}.
	 */
	public final boolean containsMainEndPointTaker() {
		return (mainEndPointTaker != null);
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
	 * {@inheritDoc}
	 */
	@Override
	public void noteClose() {}
	
	//method
	/**
	 * Removes the {@link IEndPointTaker} with the given name from the current {@link BaseServer}.
	 * 
	 * @param name
	 * @throws InvalidArgumentException
	 * if the current {@link BaseServer} does not contain a {@link IEndPointTaker} with the given name.
	 */
	public final void removeEndPointTaker(final String name) {
		
		final var endPointTaker = this.endPointTakers.removeAndGetRefFirst(ept -> ept.hasName(name));
		
		//Handles the case that the concerning IEndPointTaker
		//has been the main IEndPointTaker of the current {@link Server}.
		if (endPointTaker == mainEndPointTaker) {
			mainEndPointTaker = null;
		}
	}
	
	//method
	/**
	 * Lets the current {@link BaseServer} take the given endPoint.
	 * 
	 * @param endPoint
	 * @throws ArgumentDoesNotHaveAttributeException if
	 * the current {@link BaseServer} does not have an arbitrary endPointTaker
	 * or does not contain an endPointTaker
	 * with the same name as the target of the given endPointTaker. 
	 */
	public final void takeEndPoint(final EndPoint endPoint) {
		
		//Handles the case that the given endPoint does not have a target.
		if (!endPoint.hasTarget()) {
			getRefMainEndPointTaker().takeEndPoint(endPoint);
			
		//Handles the case that the given endPoint has a target.
		} else {
			endPointTakers.getRefFirst(ept -> ept.hasName(endPoint.getTarget())).takeEndPoint(endPoint);
		}
	}
	
	//method
	/**
	 * @return the main {@link IEndPointTaker} of the current {@link BaseServer}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BaseServer} does not contain a main {@link IEndPointTaker}.
	 */
	private IEndPointTaker getRefMainEndPointTaker() {
		
		//Asserts that the current Server has a main IEndPointTaker.
		//For a better performance, this implementation does not use all comfortable methods.
		if (mainEndPointTaker == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, "main IEndPointTaker");
		}
		
		return mainEndPointTaker;
	}
}
