package ch.nolix.core.container.cachingcontainer;

import java.util.Optional;

import ch.nolix.core.container.arraylist.AbstractExtendedContainer;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.datastructure.pair.Pair;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.cachingcontainer.ICachingContainer;
import ch.nolix.coreapi.container.iterator.CopyableIterator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

public final class CachingContainer<E> extends AbstractExtendedContainer<E> implements ICachingContainer<E> {

  private static final String AUTO_ID_PREFIX = "Z";

  private long autoIdCounter = 1;

  private final LinkedList<Pair<String, E>> elements = LinkedList.createEmpty();

  @Override
  public boolean containsWithId(final String id) {
    return elements.containsAny(e -> e.getStoredElement1().equals(id));
  }

  @Override
  public int getCount() {
    return elements.getCount();
  }

  @Override
  public String getIdOf(final E element) {
    return elements.getStoredFirst(e -> e.getStoredElement2().equals(element)).getStoredElement1();
  }

  public Optional<String> getOptionalIdOf(final E element) {

    final var pair = elements.getOptionalStoredFirst(e -> e.getStoredElement2() == element);

    if (pair.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(pair.get().getStoredElement1());
  }

  @Override
  public E getStoredAtOneBasedIndex(final int oneBasedIndex) {
    return elements.getStoredAtOneBasedIndex(oneBasedIndex).getStoredElement2();
  }

  @Override
  public E getStoredById(final String id) {
    return elements.getStoredFirst(e -> e.getStoredElement1().equals(id)).getStoredElement2();
  }

  @Override
  public boolean isMaterialized() {
    return true;
  }

  @Override
  public CopyableIterator<E> iterator() {
    return new CachingContainerIterator<>(elements.iterator());
  }

  @Override
  public String registerAndGetId(final E element) {

    Validator.assertThat(element).thatIsNamed(LowerCaseVariableCatalog.ELEMENT).isNotNull();

    assertDoesNotContain(element);

    final var id = createNextAutoId();
    elements.addAtEnd(new Pair<>(id, element));

    return id;
  }

  @Override
  public void registerAtId(final String id, final E element) {

    Validator.assertThat(id).thatIsNamed(LowerCaseVariableCatalog.ID).isNotBlank();
    Validator.assertThat(element).thatIsNamed(LowerCaseVariableCatalog.ELEMENT).isNotNull();

    assertDoesNotContainId(id);
    assertDoesNotContain(element);

    elements.addAtEnd(new Pair<>(id, element));
  }

  @Override
  public String registerIfNotRegisteredAndGetId(final E element) {

    final var pair = elements.getOptionalStoredFirst(e -> e.hasElement2(element));

    if (pair.isEmpty()) {

      Validator.assertThat(element).thatIsNamed(LowerCaseVariableCatalog.ELEMENT).isNotNull();

      final var id = createNextAutoId();
      elements.addAtEnd(new Pair<>(id, element));

      return id;
    }

    return pair.get().getStoredElement1();
  }

  private void assertDoesNotContain(final E element) {
    if (contains(element)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        this,
        "contains already the given element '" + element + "'");
    }
  }

  private void assertDoesNotContainId(final String id) {
    if (containsWithId(id)) {
      throw InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        LowerCaseVariableCatalog.ID,
        id,
        "is already used");
    }
  }

  private String createNextAutoId() {

    while (containsWithId(AUTO_ID_PREFIX + autoIdCounter)) {
      autoIdCounter++;
    }

    final var nextAutoId = autoIdCounter;

    autoIdCounter++;

    return String.valueOf(AUTO_ID_PREFIX + nextAutoId);
  }
}
