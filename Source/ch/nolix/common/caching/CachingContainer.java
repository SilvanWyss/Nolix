//package declaration
package ch.nolix.common.caching;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.pair.Pair;
import ch.nolix.common.validator.Validator;

//class
public final class CachingContainer<E> implements IContainer<E> {
	
	//constant
	private static final String AUTO_ID_PREFIX = "$";
	
	//attribute
	private long autoIdCounter = 0;
	
	//multi-attribute
	private final LinkedList<Pair<String, E>> elements = new LinkedList<>();
	
	//method
	public boolean containsId(final String id) {
		return elements.contains(e -> e.getRefElement1().equals(id));
	}
	
	//method
	public String getIdOf(final E element) {
		return elements.getRefFirst(e -> e.getRefElement2().equals(element)).getRefElement1();
	}
	
	//method
	public String getIdOfOrNull(final E element) {
		
		final var pair = elements.getRefFirstOrNull(e -> e.getRefElement2().equals(element));
		
		if (pair == null) {
			return null;
		}
		
		return pair.getRefElement1();
	}
	
	//method
	public E getRefById(final String id) {
		return elements.getRefFirst(e -> e.getRefElement1().equals(id)).getRefElement2();
	}
	
	//method
	@Override
	public int getElementCount() {
		return elements.getElementCount();
	}
	
	//method
	@Override
	public Iterator<E> iterator() {
		return new CachingContainerIterator<>(elements.iterator());
	}
	
	//method
	public void registerAtId(final String id, final E element) {
		
		Validator.assertThat(id).thatIsNamed(VariableNameCatalogue.ID).isNotBlank();
		Validator.assertThat(element).thatIsNamed(VariableNameCatalogue.ELEMENT).isNotNull();
		assertDoesNotContainId(id);
		
		elements.addAtEnd(new Pair<>(id, element));
	}
	
	//method
	public String registerIfNotRegisteredAndGetId(final E element) {
		
		final var pair = elements.getRefFirstOrNull(e -> e.hasElement2(element));
		
		if (pair == null) {
			
			Validator.assertThat(element).thatIsNamed(VariableNameCatalogue.ELEMENT).isNotNull();
			
			final var id = createNextAutoId();
			elements.addAtEnd(new Pair<>(id, element));
			
			return id;
		}
		
		return pair.getRefElement1();
	}
	
	//method
	private void assertDoesNotContainId(String id) {
		if (containsId(id)) {
			throw new InvalidArgumentException(VariableNameCatalogue.ID, id, "is already used");
		}
	}
	
	//method
	private String createNextAutoId() {
		
		autoIdCounter++;
		while (containsId(AUTO_ID_PREFIX + autoIdCounter)) {
			autoIdCounter++;
		}
		
		return String.valueOf(AUTO_ID_PREFIX + autoIdCounter);
	}
}
