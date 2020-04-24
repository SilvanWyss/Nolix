//package declaration
package ch.nolix.common.container;

//own imports
import ch.nolix.common.functionAPI.IElementTakerElementGetter;
import ch.nolix.common.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2017-06
 * @lines 110
 * @param <E> - The type of the elements of the list of a list using mediator.
 */
public final class ListUsingMediator<E> {

	//attributes
	private final LinkedList<E> linkedList;
	private final IElementTakerElementGetter<Object, E> extractor;
	
	//constructor
	/**
	 * Creates a new list using mediator with the given list and extractor.
	 * 
	 * @param list
	 * @param extractor
	 * @throws ArgumentIsNullException if the given list is null.
	 * @throws ArgumentIsNullException if the given extractor is null.
	 */
	ListUsingMediator(LinkedList<E> list, IElementTakerElementGetter<Object, E> extractor) {
		
		//Asserts that the given list is not null.
		Validator.assertThat(list).thatIsInstanceOf(LinkedList.class).isNotNull();
		
		//Asserts that the given extractor is not null.
		Validator.assertThat(extractor).thatIsNamed("extractor").isNotNull();
		
		//Sets the list of this list using mediator.
		this.linkedList = list;
		
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
	public <E2> LinkedList<E> addAtBegin(final E2... elements) {
		
		//Creates list.
		final var list = new LinkedList<E>();
		
		//Iterates the given elements.
		for (final E2 e : elements) {
			list.addAtEnd(extractor.getOutput(e));
		}
		
		//Adds the list at the begin of the list of this list using mediator.
		this.linkedList.addAtBegin(list);
		
		return this.linkedList;
	}
	
	//method
	/**
	 * Adds the given elements at the begin of the list of this list using mediator.
	 * 
	 * @param elements
	 * @return the list of this list using mediator.
	 */
	public <E2> LinkedList<E> addAtBegin(final Iterable<E2> elements) {
		
		//Creates list.
		final LinkedList<E> list = new LinkedList<>();
		elements.forEach(e -> list.addAtBegin(extractor.getOutput(e)));
		
		//Adds the list at the begin of the list of this list using mediator.
		this.linkedList.addAtBegin(list);
		
		return this.linkedList;
	}
	
	//method
	/**
	 * Adds the given elements at the end of the list of this list using mediator.
	 * 
	 * @param elements
	 * @return the list of this list using mediator.
	 */
	@SuppressWarnings("unchecked")
	public <E2> LinkedList<E> addAtEnd(final E2... elements) {
		
		//Iterates the given elements.
		for (final E2 e : elements) {
			linkedList.addAtEnd(extractor.getOutput(e));
		}
		
		return linkedList;
	}

	//method
	/**
	 * Adds the given elements at the end of the list of this list using mediator.
	 * 
	 * @param elements
	 * @return the list of this list using mediator.
	 */
	public <E2> LinkedList<E> addAtEnd(final Iterable<E2> elements) {
		
		elements.forEach(e -> linkedList.addAtEnd(extractor.getOutput(e)));
		
		return linkedList;
	}
}
