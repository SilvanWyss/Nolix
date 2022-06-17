//package declaration
package ch.nolix.core.container;

import ch.nolix.core.containerapi.IContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.functionuniversalapi.IElementTakerComparableGetter;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
/**
 * A {@link SingleContainer} is a {@link Container} that is empty or stores 1 element.
 * A {@link SingleContainer} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2020-01-11
 * @param <E> is the type of the element of a {@link SingleContainer}.
 */
public final class SingleContainer<E> extends Container<E> {
	
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
		
		//Asserts that the given element is not null.
		GlobalValidator.assertThat(element).thatIsNamed(LowerCaseCatalogue.ELEMENT).isNotNull();
		
		//Sets the element of the current SingleContainer.
		this.element = element;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SingleContainerIterator<E> iterator() {
		return new SingleContainerIterator<>(element);
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getElementCount() {
		
		//Handles the case that the current SingleContainer is empty.
		if (element == null) {
			return 0;
		}
		
		//Handles the case that the current SingleContainer contains an element.
		return 1;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public E getRefAt(final int index) {
		
		GlobalValidator.assertThat(index).thatIsNamed(LowerCaseCatalogue.INDEX).isEqualTo(1);
		
		return getRefElement();
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * @return the element of the current {@link SingleContainer}.
	 * @throws EmptyArgumentException if the current {@link SingleContainer} is empty.
	 */
	public E getRefElement() {
		
		//Asserts that the current SingleContainer is not empty.
		if (element == null) {
			throw new EmptyArgumentException(this);
		}
		
		//Returns the element of the current SingleContainer.
		return element;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public E getRefLast() {
		return getRefElement();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E2> IContainer<E> toOrderedList(final IElementTakerComparableGetter<E, E2> norm) {
		return LinkedList.fromIterable(this).toOrderedList(norm);
	}
}
