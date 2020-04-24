//package declaration
package ch.nolix.common.endPoint2;

//own imports
import ch.nolix.common.closableElement.ClosableElement;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidArgumentException.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentException.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.skillAPI.Clearable;
import ch.nolix.common.validator.Validator;

//class
/**
 * A {@link Server} contains {@link IEndPointTaker}s.
 * A {@link Server} is clearable and closable.
 * 
 * @author Silvan Wyss
 * @month 2017-05
 * @lines 180
 */
public class Server extends ClosableElement implements Clearable<Server> {
	
	//optional attribute
	private IEndPointTaker mainEndPointTaker;
	
	//multi-attribute
	private final LinkedList<IEndPointTaker> endPointTaker = new LinkedList<>();
	
	//method
	/**
	 * Adds the given endPointTaker to the current {@link Server}.
	 * 
	 * @param endPointTaker
	 * @throws InvalidArgumentException if the current {@link Server} contains already a {@link IEndPointTaker}
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
		
		this.endPointTaker.addAtEnd(endPointTaker);
	}

	//method
	/**
	 * Adds the given mainEndPointTaker to the current {@link Server}.
	 * A main {@link IEndPointTaker} takes all {@link EndPoints}s without target.
	 * 
	 * @param mainEndPointTaker
	 * @throws InvalidArgumentException if the current {@link Server} contains already a main {@link EndPointTaker}.
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
	 * Removes all {@link IEndPointTaker}s from the current {@link Server}.
	 * 
	 * @return the current {@link Server}.
	 */
	@Override
	public final Server clear() {
		
		endPointTaker.clear();
		mainEndPointTaker = null;
		
		return this;
	}
	
	//method
	/**
	 * @param name
	 * @return true if the current {@link Server} contains a {@link IEndPointTaker} with the given name.
	 */
	public final boolean containsEndPointTaker(final String name) {
		return endPointTaker.contains(ept -> ept.hasName(name));
	}
	
	//method
	/**
	 * @return true if the current {@link Server} contains a main {@link IEndPointTaker}.
	 */
	public final boolean containsMainEndPointTaker() {
		return (mainEndPointTaker != null);
	}
	
	//method
	/**
	 * @return true if the current {@link Server} does not contain a {@link IEndPointTaker}.
	 */
	@Override
	public final boolean isEmpty() {
		return endPointTaker.isEmpty();
	}
	
	//method
	/**
	 * Removes the {@link IEndPointTaker} with the given name from the current {@link Server}.
	 * 
	 * @param name
	 * @throws InvalidArgumentException
	 * if the current {@link Server} does not contain a {@link IEndPointTaker} with the given name.
	 */
	public final void removeEndPointTaker(final String name) {
		
		final var endPointTaker = this.endPointTaker.removeAndGetRefFirst(ept -> ept.hasName(name));
		
		//Handles the case that the concerning IEndPointTaker
		//has been the main IEndPointTaker of the current {@link Server}.
		if (endPointTaker == mainEndPointTaker) {
			mainEndPointTaker = null;
		}
	}
	
	//method
	/**
	 * Lets the current {@link Server} take the given endPoint.
	 * 
	 * @param endPoint
	 * @throws ArgumentIsNullException if the given endPoint is null.
	 * @throws ArgumentDoesNotHaveAttributeException if
	 * the current {@link Server} does not have an arbitrary endPointTaker
	 * or does not contain an endPointTaker
	 * with the same name as the target of the given endPointTaker. 
	 */
	public final void takeEndPoint(final EndPoint endPoint) {
		
		//Asserts that the given endPoint is not null.
		Validator.assertThat(endPoint).thatIsNamed(EndPoint.class).isNotNull();
		
		//Asserts that the given endPoint is alive.
		if (endPoint.isClosed()) {
			throw new InvalidArgumentException(endPoint, "is closed");
		}
		
		//Handles the case that the given endPoint does not have a target.
		if (!endPoint.hasTarget()) {
			getRefMainEndPointTaker().takeEndPoint(endPoint);
		}
		
		//Handles the case that the given endPoint has a target.
		else {
			endPointTaker.getRefFirst(ept -> ept.hasName(endPoint.getTarget())).takeEndPoint(endPoint);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteClose() {}
	
	//method
	/**
	 * @return the main {@link IEndPointTaker} of the current {@link Server}.
	 * @throws UnexistingAttribtueException
	 * if the current {@link Server} does not contain a main {@link IEndPointTaker}.
	 */
	private IEndPointTaker getRefMainEndPointTaker() {
		
		//Asserts that the current Server has a main IEndPointTaker.
		//For a better performance, this method does not use all comfortable methods.
		if (mainEndPointTaker == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, "main IEndPointTaker");
		}
		
		return mainEndPointTaker;
	}
}
