//package declaration
package ch.nolix.core.caching;

//Java imports
import java.util.Iterator;

import ch.nolix.core.container.main.Container;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.container.main.SingleContainer;
import ch.nolix.core.container.pair.Pair;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerComparableGetter;

//class
public final class CachingContainer<E> extends Container<E> {
	
	//constant
	private static final String AUTO_ID_PREFIX = "Z";
	
	//attribute
	private long autoIdCounter;
	
	//multi-attribute
	private final LinkedList<Pair<String, E>> elements = new LinkedList<>();
	
	//method
	public boolean containsWithId(final String id) {
		return elements.containsAny(e -> e.getRefElement1().equals(id));
	}
	
	//method
	@Override
	public int getElementCount() {
		return elements.getElementCount();
	}
	
	//method
	public String getIdOf(final E element) {
		return elements.getRefFirst(e -> e.getRefElement2().equals(element)).getRefElement1();
	}
	
	//method
	public SingleContainer<String> getOptionalIdOf(final E element) {
		
		final var pair = elements.getRefFirstOrNull(e -> e.getRefElement2() == element);
		
		if (pair == null) {
			return new SingleContainer<>();
		}
		
		return new SingleContainer<>(pair.getRefElement1());
	}
	
	//method
	@Override
	public E getRefAt1BasedIndex(final int p1BasedIndex) {
		return elements.getRefAt1BasedIndex(p1BasedIndex).getRefElement2();
	}
	
	//method
	public E getRefById(final String id) {
		return elements.getRefFirst(e -> e.getRefElement1().equals(id)).getRefElement2();
	}
	
	//method
	@Override
	public E getRefLast() {
		return elements.getRefLast().getRefElement2();
	}
	
	//method
	@Override
	public Iterator<E> iterator() {
		return new CachingContainerIterator<>(elements.iterator());
	}
	
	//method
	public String registerAndGetId(final E element) {
		
		GlobalValidator.assertThat(element).thatIsNamed(LowerCaseCatalogue.ELEMENT).isNotNull();
		
		assertDoesNotContain(element);
		
		final var id = createNextAutoId();
		elements.addAtEnd(new Pair<>(id, element));
		
		return id;
	}

	//method
	public void registerAtId(final String id, final E element) {
		
		GlobalValidator.assertThat(id).thatIsNamed(LowerCaseCatalogue.ID).isNotBlank();
		GlobalValidator.assertThat(element).thatIsNamed(LowerCaseCatalogue.ELEMENT).isNotNull();
		
		assertDoesNotContainId(id);
		assertDoesNotContain(element);
		
		elements.addAtEnd(new Pair<>(id, element));
	}
	
	//method
	public String registerIfNotRegisteredAndGetId(final E element) {
		
		final var pair = elements.getRefFirstOrNull(e -> e.hasElement2(element));
		
		if (pair == null) {
			
			GlobalValidator.assertThat(element).thatIsNamed(LowerCaseCatalogue.ELEMENT).isNotNull();
			
			final var id = createNextAutoId();
			elements.addAtEnd(new Pair<>(id, element));
			
			return id;
		}
		
		return pair.getRefElement1();
	}
	
	//method
	@Override
	public <E2> IContainer<E> toOrderedList(final IElementTakerComparableGetter<E, E2> norm) {
		return LinkedList.fromIterable(this).toOrderedList(norm);
	}
	
	//method
	private void assertDoesNotContain(final E element) {
		if (contains(element)) {
			throw
			InvalidArgumentException.forArgumentAndErrorPredicate(
				this,
				"contains already the given element '" + element + "'"
			);
		}
	}
	
	//method
	private void assertDoesNotContainId(final String id) {
		if (containsWithId(id)) {
			throw
			InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
				LowerCaseCatalogue.ID,
				id,
				"is already used"
			);
		}
	}
	
	//method
	private String createNextAutoId() {
		
		autoIdCounter++;
		while (containsWithId(AUTO_ID_PREFIX + autoIdCounter)) {
			autoIdCounter++;
		}
		
		return String.valueOf(AUTO_ID_PREFIX + autoIdCounter);
	}
}
