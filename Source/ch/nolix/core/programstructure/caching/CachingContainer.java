//package declaration
package ch.nolix.core.programstructure.caching;

//Java imports
import java.util.Optional;
import java.util.function.Function;

//own imports
import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.pair.Pair;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.programstructureapi.cachingapi.ICachingContainer;

//class
public final class CachingContainer<E> extends Container<E> implements ICachingContainer<E> {

  //constant
  private static final String AUTO_ID_PREFIX = "Z";

  //attribute
  private long autoIdCounter;

  //multi-attribute
  private final LinkedList<Pair<String, E>> elements = LinkedList.createEmpty();

  //method
  @Override
  public boolean containsWithId(final String id) {
    return elements.containsAny(e -> e.getStoredElement1().equals(id));
  }

  //method
  @Override
  public int getCount() {
    return elements.getCount();
  }

  //method
  @Override
  public String getIdOf(final E element) {
    return elements.getStoredFirst(e -> e.getStoredElement2().equals(element)).getStoredElement1();
  }

  //method
  public Optional<String> getOptionalIdOf(final E element) {

    final var pair = elements.getOptionalStoredFirst(e -> e.getStoredElement2() == element);

    if (pair.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(pair.get().getStoredElement1());
  }

  //method
  @Override
  public E getStoredAt1BasedIndex(final int p1BasedIndex) {
    return elements.getStoredAt1BasedIndex(p1BasedIndex).getStoredElement2();
  }

  //method
  @Override
  public E getStoredById(final String id) {
    return elements.getStoredFirst(e -> e.getStoredElement1().equals(id)).getStoredElement2();
  }

  //method
  @Override
  public E getStoredLast() {
    return elements.getStoredLast().getStoredElement2();
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

    GlobalValidator.assertThat(element).thatIsNamed(LowerCaseVariableCatalogue.ELEMENT).isNotNull();

    assertDoesNotContain(element);

    final var id = createNextAutoId();
    elements.addAtEnd(new Pair<>(id, element));

    return id;
  }

  //method
  @Override
  public void registerAtId(final String id, final E element) {

    GlobalValidator.assertThat(id).thatIsNamed(LowerCaseVariableCatalogue.ID).isNotBlank();
    GlobalValidator.assertThat(element).thatIsNamed(LowerCaseVariableCatalogue.ELEMENT).isNotNull();

    assertDoesNotContainId(id);
    assertDoesNotContain(element);

    elements.addAtEnd(new Pair<>(id, element));
  }

  //method
  @Override
  public String registerIfNotRegisteredAndGetId(final E element) {

    final var pair = elements.getOptionalStoredFirst(e -> e.hasElement2(element));

    if (pair.isEmpty()) {

      GlobalValidator.assertThat(element).thatIsNamed(LowerCaseVariableCatalogue.ELEMENT).isNotNull();

      final var id = createNextAutoId();
      elements.addAtEnd(new Pair<>(id, element));

      return id;
    }

    return pair.get().getStoredElement1();
  }

  //method
  @Override
  public <C extends Comparable<C>> IContainer<E> toOrderedList(final Function<E, C> norm) {
    return LinkedList.fromIterable(this).toOrderedList(norm);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  protected <E2> ILinkedList<E2> createEmptyMutableList(final Marker<E2> marker) {
    return LinkedList.createEmpty();
  }

  //method
  private void assertDoesNotContain(final E element) {
    if (contains(element)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        this,
        "contains already the given element '" + element + "'");
    }
  }

  //method
  private void assertDoesNotContainId(final String id) {
    if (containsWithId(id)) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        LowerCaseVariableCatalogue.ID,
        id,
        "is already used");
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
