//package declaration
package ch.nolix.common.caching;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.SingleContainer;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.pair.Pair;
import ch.nolix.common.validator.Validator;

//class
public final class CachingContainer<E> implements IContainer<E> {
	
	//constant
	private static final String AUTO_ID_PREFIX = "Z";
	
	//attribute
	private long autoIdCounter;
	
	//multi-attribute
	private final LinkedList<Pair<String, E>> elements = new LinkedList<>();
	
	//method
	public boolean containsWithId(final String id) {
		return elements.contains(e -> e.getRefElement1().equals(id));
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
	public SingleContainer<String> getOptionallyIdOf(final E element) {
		
		final var pair = elements.getRefFirstOrNull(e -> e.getRefElement2() == element);
		
		if (pair == null) {
			return new SingleContainer<>();
		}
		
		return new SingleContainer<>(pair.getRefElement1());
	}
	
	//method
	@Override
	public E getRefAt(final int index) {
		return elements.getRefAt(index).getRefElement2();
	}
	
	//method
	public E getRefById(final String id) {
		return elements.getRefFirst(e -> e.getRefElement1().equals(id)).getRefElement2();
	}
	
	//method
	@Override
	public Iterator<E> iterator() {
		return new CachingContainerIterator<>(elements.iterator());
	}
	
	//method
	public String registerAndGetId(final E element) {
		
		Validator.assertThat(element).thatIsNamed(LowerCaseCatalogue.ELEMENT).isNotNull();
		
		assertDoesNotContain(element);
		
		final var id = createNextAutoId();
		elements.addAtEnd(new Pair<>(id, element));
		
		return id;
	}

	//method
	public void registerAtId(final String id, final E element) {
		
		Validator.assertThat(id).thatIsNamed(LowerCaseCatalogue.ID).isNotBlank();
		Validator.assertThat(element).thatIsNamed(LowerCaseCatalogue.ELEMENT).isNotNull();
		
		assertDoesNotContainId(id);
		assertDoesNotContain(element);
		
		elements.addAtEnd(new Pair<>(id, element));
	}
	
	//method
	public String registerIfNotRegisteredAndGetId(final E element) {
		
		final var pair = elements.getRefFirstOrNull(e -> e.hasElement2(element));
		
		if (pair == null) {
			
			Validator.assertThat(element).thatIsNamed(LowerCaseCatalogue.ELEMENT).isNotNull();
			
			final var id = createNextAutoId();
			elements.addAtEnd(new Pair<>(id, element));
			
			return id;
		}
		
		return pair.getRefElement1();
	}
	
	//method
	private void assertDoesNotContain(final E element) {
		if (contains(element)) {
			throw new InvalidArgumentException(this, "contains already the given element '" + element + "'");
		}
	}
	
	//method
	private void assertDoesNotContainId(final String id) {
		if (containsWithId(id)) {
			throw new InvalidArgumentException(LowerCaseCatalogue.ID, id, "is already used");
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
