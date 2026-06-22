/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.independent.immutablelist;

import java.util.Iterator;

import ch.nolix.base.independent.arraytool.ArrayValidator;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements of a {@link ImmutableList}.
 */
public final class ImmutableList<E> implements Iterable<E> {
  private static final ArrayValidator ARRAY_VALIDATOR = new ArrayValidator();

  private final E[] elements;

  @SuppressWarnings("unchecked")
  private ImmutableList() {
    elements = (E[]) new Object[0];

    ARRAY_VALIDATOR.assertDoesNotContainNull(elements);
  }

  private ImmutableList(final E[] paramElements) {
    elements = paramElements.clone();

    ARRAY_VALIDATOR.assertDoesNotContainNull(elements);
  }

  public static <T> ImmutableList<T> createEmptyList() {
    return new ImmutableList<>();
  }

  public static <T> ImmutableList<T> withElements(final T[] array) {
    return new ImmutableList<>(array);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterator<E> iterator() {
    return ImmutableListIterator.forArray(elements);
  }
}
