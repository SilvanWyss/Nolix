/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.foundation.immutablelist;

import java.util.Iterator;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of a {@link SimpleImmutableList}.
 */
public final class SimpleImmutableList<E> implements Iterable<E> {
  private final E[] elements;

  @SuppressWarnings("unchecked")
  private SimpleImmutableList() {
    elements = (E[]) new Object[0];
  }

  private SimpleImmutableList(final E[] paramElements) {
    elements = paramElements.clone();
  }

  public static <T> SimpleImmutableList<T> createEmptyList() {
    return new SimpleImmutableList<>();
  }

  public static <T> SimpleImmutableList<T> withElements(final T[] array) {
    return new SimpleImmutableList<>(array);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterator<E> iterator() {
    return SimpleImmutableListIterator.forArray(elements);
  }
}
