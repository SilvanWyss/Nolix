package ch.nolix.core.container.singlecontainer;

import java.util.NoSuchElementException;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.coreapi.container.iterator.CopyableIterator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 */
public final class SingleContainerIterator<E> implements CopyableIterator<E> {
  private E nullableElement;

  private SingleContainerIterator(final E nullableElement) {
    this.nullableElement = nullableElement;
  }

  public static <T> SingleContainerIterator<T> forNullableElement(final T nullableElement) {
    return new SingleContainerIterator<>(nullableElement);
  }

  @Override
  public CopyableIterator<E> getCopy() {
    return forNullableElement(nullableElement);
  }

  @Override
  public boolean hasNext() {
    return (nullableElement != null);
  }

  @Override
  public E next() {
    assertHasNext();

    final var localElement = nullableElement;

    nullableElement = null;

    return localElement;
  }

  private void assertHasNext() throws NoSuchElementException {
    if (!hasNext()) {
      throw //
      ArgumentDoesNotHaveAttributeException
        .forArgumentAndAttributeName(this, LowerCaseVariableCatalog.NEXT_ELEMENT)
        .toNoSuchElementException();
    }
  }
}
