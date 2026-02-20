/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.container.singlecontainer;

import java.util.NoSuchElementException;

import ch.nolix.base.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.container.iterator.CopyableIterator;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the element of the parent {@link SingleContainer}
 *            of a {@link SingleContainerIterator}.
 */
public final class SingleContainerIterator<E> implements CopyableIterator<E> {
  private E nullableElement;

  private SingleContainerIterator(final E nullableElement) {
    this.nullableElement = nullableElement;
  }

  public static <T> SingleContainerIterator<T> forNullableElement(final T nullableElement) {
    return new SingleContainerIterator<>(nullableElement);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> getCopy() {
    return forNullableElement(nullableElement);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasNext() {
    return (nullableElement != null);
  }

  /**
   * {@inheritDoc}
   */
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
