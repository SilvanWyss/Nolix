//package declaration
package ch.nolix.common.container;

//own imports
import ch.nolix.common.functionapi.IElementTakerElementGetter;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @date 2017-06-18
 * @lines 120
 * @param <E> The type of the elements of the elements of the {@link LinkedList} of the {@link ListUsingMediator}.
 */
public final class ListUsingMediator<E> {
	
	//attributes
	private final LinkedList<E> linkedList;
	private final IElementTakerElementGetter<Object, E> extractor;
	
	//constructor
	/**
	 * Creates a new {@link ListUsingMediator} with the given list and extractor.
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
		
		//Sets the list of the current ListUsingMediator.
		this.linkedList = list;
		
		//Sets the extractor of the current ListUsingMediator.
		this.extractor = extractor;
	}
	
	//method
	/**
	 * Adds the given elements at the begin of the {@link LinkedList} of the current {@link ListUsingMediator}.
	 * 
	 * @param elements
	 * @param <E2> is the type of the given elements.
	 * @return the {@link LinkedList} of the current {@link ListUsingMediator}.
	 */
	@SuppressWarnings("unchecked")
	public <E2> LinkedList<E> addAtBegin(final E2... elements) {
		
		//Creates list.
		final var list = new LinkedList<E>();
		
		//Iterates the given elements.
		for (final E2 e : elements) {
			list.addAtEnd(extractor.getOutput(e));
		}
		
		//Adds the list at the begin of the list of the current ListUsingMediator.
		this.linkedList.addAtBegin(list);
		
		return this.linkedList;
	}
	
	//method
	/**
	 * Adds the given elements at the begin of the list of the current {@link ListUsingMediator}.
	 * 
	 * @param elements
	 * @param <E2> is the type of the given elements.
	 * @return the {@link LinkedList} of the current {@link ListUsingMediator}.
	 */
	public <E2> LinkedList<E> addAtBegin(final Iterable<E2> elements) {
		
		//Creates list.
		final LinkedList<E> list = new LinkedList<>();
		elements.forEach(e -> list.addAtBegin(extractor.getOutput(e)));
		
		//Adds the list at the begin of the list of the current ListUsingMediator.
		this.linkedList.addAtBegin(list);
		
		return this.linkedList;
	}
	
	//method
	/**
	 * Adds the given elements at the end of the list of the current {@link ListUsingMediator}.
	 * 
	 * @param elements
	 * @param <E2> is the type of the given elements.
	 * @return the {@link LinkedList} of the current {@link ListUsingMediator}.
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
	 * Adds the given elements at the end of the list of the current {@link ListUsingMediator}.
	 * 
	 * @param elements
	 * @param <E2> is the type of the given elements.
	 * @return the {@link LinkedList} of the current {@link ListUsingMediator}.
	 */
	public <E2> LinkedList<E> addAtEnd(final Iterable<E2> elements) {
		
		elements.forEach(e -> linkedList.addAtEnd(extractor.getOutput(e)));
		
		return linkedList;
	}
}
