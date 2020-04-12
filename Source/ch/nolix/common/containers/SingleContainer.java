//package declaration
package ch.nolix.common.containers;

//own imports
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentExceptions.EmptyArgumentException;
import ch.nolix.common.validator.Validator;

//class
/**
 * A {@link SingleContainer} is a {@link IContainer} that is empty or stores 1 element.
 * A {@link SingleContainer} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2020-01
 * @param <E> The type of the element of a {@link SingleContainer}.
 * @lines 80
 */
public final class SingleContainer<E> implements IContainer<E> {
	
	//optional attribute
	private final E element;
	
	//constructor
	/**
	 * Creates a new {@link SingleContainer} that is empty.
	 */
	public SingleContainer() {
		element = null;
	}
	
	//constructor
	/**
	 * Creates a new {@link SingleContainer} with the given element.
	 * 
	 * @param element
	 * @throws ArgumentIsNullException if the given element is null.
	 */
	public SingleContainer(final E element) {
		
		//Checks if the given element is not null.
		Validator.assertThat(element).thatIsNamed(VariableNameCatalogue.ELEMENT).isNotNull();
		
		//Sets the element of the current SingleContainer.
		this.element = element;
	}
	
	//method
	/**
	 * @inheritDoc
	 */
	@Override
	public SingleContainerIterator<E> iterator() {
		return new SingleContainerIterator<>(this);
	}
	
	//method
	/**
	 * @return the element of the current {@link SingleContainer}.
	 * @throws EmptyArgumentException if the current {@link SingleContainer} is empty.
	 */
	public E getRefElement() {
		
		//Checks if the current SingleContainer is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
		
		//Returns the element of the current SingleContainer.
		return element;
	}
	
	//method
	/**
	 * @inheritDoc
	 */
	@Override
	public int getSize() {
		return (isEmpty() ? 0 : 1);
	}
}
