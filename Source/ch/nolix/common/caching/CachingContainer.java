//package declaration
package ch.nolix.common.caching;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.pair.Pair;
import ch.nolix.common.validator.Validator;

//class
public final class CachingContainer<E> implements IContainer<E> {
	
	//attribute
	private long currentId = 0;
	
	//multi-attribute
	private final LinkedList<Pair<Long, E>> elements = new LinkedList<>();
	
	//method
	public E getRefElementById(final long id) {
		return elements.getRefFirst(e -> e.getRefElement1() == id).getRefElement2();
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
	public long registerElementAndGetId(final E element) {
		
		Validator.assertThat(element).thatIsNamed(VariableNameCatalogue.ELEMENT).isNotNull();
		
		final var nextId = createNextId();
		elements.addAtEnd(new Pair<>(nextId, element));
		
		return nextId;
	}
	
	//method
	private long createNextId() {
		
		currentId++;
		
		return currentId;
	}
}
