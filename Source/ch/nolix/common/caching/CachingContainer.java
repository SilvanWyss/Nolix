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
	public E getRefElementById(final String id) {
		return elements.getRefFirst(e -> e.getRefElement1().equals(id)).getRefElement2();
	}
	
	//method
	@Override
	public int getSize() {
		return elements.getSize();
	}
	
	//method
	@Override
	public Iterator<E> iterator() {
		return new CachingContainerIterator<>(elements.iterator());
	}
	
	//method
	public String registerElementAndGetId(final E element) {
		
		Validator.assertThat(element).thatIsNamed(VariableNameCatalogue.ELEMENT).isNotNull();
		
		final var id = createNextAutoId();
		elements.addAtEnd(new Pair<>(id, element));
		
		return id;
	}
	
	//method
	public void registerElementWithId(final String id, final E element) {
		
		Validator.assertThat(element).thatIsNamed(VariableNameCatalogue.ELEMENT).isNotNull();
		
		assertDoesNotContainId(id);
	}
	
	//method
	private String createNextAutoId() {
		
		autoIdCounter++;
		while (containsId(AUTO_ID_PREFIX + autoIdCounter)) {
			autoIdCounter++;
		}
		
		return String.valueOf(AUTO_ID_PREFIX + autoIdCounter);
	}
	
	//method
	private void assertDoesNotContainId(String id) {
		if (containsId(id)) {
			throw new InvalidArgumentException(VariableNameCatalogue.ID, id, "is already used");
		}
	}
}
