//package declaration
package ch.nolix.core.programstructure.caching;

//own imports
import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.pair.Pair;
import ch.nolix.core.container.singlecontainer.SingleContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerElementGetter;
import ch.nolix.coreapi.programstructureapi.cachingapi.ICachingContainer;

//class
public final class CachingContainer<E> extends Container<E> implements ICachingContainer<E> {
	
	//constant
	private static final String AUTO_ID_PREFIX = "Z";
	
	//attribute
	private long autoIdCounter;
	
	//multi-attribute
	private final LinkedList<Pair<String, E>> elements = new LinkedList<>();
	
	//method
	@Override
	public boolean containsWithId(final String id) {
		return elements.containsAny(e -> e.getOriElement1().equals(id));
	}
	
	//method
	@Override
	public int getElementCount() {
		return elements.getElementCount();
	}
	
	//method
	@Override
	public String getIdOf(final E element) {
		return elements.getOriFirst(e -> e.getOriElement2().equals(element)).getOriElement1();
	}
	
	//method
	public SingleContainer<String> getOptionalIdOf(final E element) {
		
		final var pair = elements.getOriFirstOrNull(e -> e.getOriElement2() == element);
		
		if (pair == null) {
			return new SingleContainer<>();
		}
		
		return new SingleContainer<>(pair.getOriElement1());
	}
	
	//method
	@Override
	public E getOriAt1BasedIndex(final int p1BasedIndex) {
		return elements.getOriAt1BasedIndex(p1BasedIndex).getOriElement2();
	}
	
	//method
	@Override
	public E getOriById(final String id) {
		return elements.getOriFirst(e -> e.getOriElement1().equals(id)).getOriElement2();
	}
	
	//method
	@Override
	public E getOriLast() {
		return elements.getOriLast().getOriElement2();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isMaterialized() {
		return true;
	}
	
	//method
	@Override
	public CopyableIterator<E> iterator() {
		return new CachingContainerIterator<>(elements.iterator());
	}
	
	//method
	@Override
	public String registerAndGetId(final E element) {
		
		GlobalValidator.assertThat(element).thatIsNamed(LowerCaseCatalogue.ELEMENT).isNotNull();
		
		assertDoesNotContain(element);
		
		final var id = createNextAutoId();
		elements.addAtEnd(new Pair<>(id, element));
		
		return id;
	}

	//method
	@Override
	public void registerAtId(final String id, final E element) {
		
		GlobalValidator.assertThat(id).thatIsNamed(LowerCaseCatalogue.ID).isNotBlank();
		GlobalValidator.assertThat(element).thatIsNamed(LowerCaseCatalogue.ELEMENT).isNotNull();
		
		assertDoesNotContainId(id);
		assertDoesNotContain(element);
		
		elements.addAtEnd(new Pair<>(id, element));
	}
	
	//method
	@Override
	public String registerIfNotRegisteredAndGetId(final E element) {
		
		final var pair = elements.getOriFirstOrNull(e -> e.hasElement2(element));
		
		if (pair == null) {
			
			GlobalValidator.assertThat(element).thatIsNamed(LowerCaseCatalogue.ELEMENT).isNotNull();
			
			final var id = createNextAutoId();
			elements.addAtEnd(new Pair<>(id, element));
			
			return id;
		}
		
		return pair.getOriElement1();
	}
	
	//method
	@Override
	public <C extends Comparable<C>> IContainer<E> toOrderedList(final IElementTakerElementGetter<E, C> norm) {
		return LinkedList.fromIterable(this).toOrderedList(norm);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected <E2> ILinkedList<E2> createEmptyMutableList(final Marker<E2> marker) {
		return new LinkedList<>();
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
