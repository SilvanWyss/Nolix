//package declaration
package ch.nolix.core.container;

import ch.nolix.core.functionAPI.IElementTakerElementGetter;
import ch.nolix.primitive.validator2.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 110
 * @param <E> - The type of the elements of the list of a list using mediator.
 */
public final class ListUsingMediator<E> {

	//attributes
	private final List<E> list;
	private final IElementTakerElementGetter<Object, E> extractor;
	
	//package-visible constructor
	/**
	 * Creates a new list using mediator with the given list and extractor.
	 * 
	 * @param list
	 * @param extractor
	 * @throws NullArgumentException if the given list is not an instance.
	 * @throws NullArgumentException if the given extractor is not an instance.
	 */
	ListUsingMediator(List<E> list, IElementTakerElementGetter<Object, E> extractor) {
		
		//Checks if the given list is an instance.
		Validator.suppose(list).thatIsInstanceOf(List.class).isInstance();
		
		//Checks if the given extractor is an instance.
		Validator.suppose(extractor).thatIsNamed("extractor").isInstance();
		
		//Sets the list of this list using mediator.
		this.list = list;
		
		//Sets the extractor of this list using mediator.
		this.extractor = extractor;
	}
	
	//method
	/**
	 * Adds the given elements at the begin of the list of this list using mediator.
	 * 
	 * @param elements
	 * @return the list of this list using mediator.
	 */
	@SuppressWarnings("unchecked")
	public <E2> List<E> addAtBegin(final E2... elements) {
		
		//Creates list.
		final List<E> list = new List<E>();
		
		//Iterates the given elements.
		for (final E2 e : elements) {
			list.addAtEnd(extractor.getOutput(e));
		}
		
		//Adds the list at the begin of the list of this list using mediator.
		this.list.addAtBegin(list);
		
		return this.list;
	}
	
	//method
	/**
	 * Adds the given elements at the begin of the list of this list using mediator.
	 * 
	 * @param elements
	 * @return the list of this list using mediator.
	 */
	public <E2> List<E> addAtBegin(final Iterable<E2> elements) {
		
		//Creates list.
		final List<E> list = new List<E>();
		elements.forEach(e -> list.addAtBegin(extractor.getOutput(e)));
		
		//Adds the list at the begin of the list of this list using mediator.
		this.list.addAtBegin(list);
		
		return this.list;
	}
	
	//method
	/**
	 * Adds the given elements at the end of the list of this list using mediator.
	 * 
	 * @param elements
	 * @return the list of this list using mediator.
	 */
	@SuppressWarnings("unchecked")
	public <E2> List<E> addAtEnd(final E2... elements) {
		
		//Iterates the given elements.
		for (final E2 e : elements) {
			list.addAtEnd(extractor.getOutput(e));
		}
		
		return list;
	}

	//method
	/**
	 * Adds the given elements at the end of the list of this list using mediator.
	 * 
	 * @param elements
	 * @return the list of this list using mediator.
	 */
	public <E2> List<E> addAtEnd(final Iterable<E2> elements) {
		
		elements.forEach(e -> list.addAtEnd(extractor.getOutput(e)));
		
		return list;
	}
}
