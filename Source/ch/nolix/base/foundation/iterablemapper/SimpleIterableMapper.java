/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.foundation.iterablemapper;

import ch.nolix.base.foundation.linkedlist.SimpleLinkedList;
import ch.nolix.baseapi.foundation.iterablemapper.ISimpleIterableMapper;

/**
 * @author Silvan Wyss
 */
public final class SimpleIterableMapper implements ISimpleIterableMapper {
  /**
   * {@inheritDoc}
   */
  @Override
  public Iterable<Byte> toIterable(final byte[] values) {
    // Asserts that the given values is not null.
    if (values == null) {
      throw new IllegalArgumentException("The given values is null.");
    }

    final SimpleLinkedList<Byte> valueList = SimpleLinkedList.createEmpty();

    // Iterates the given elements.
    for (final var v : values) {
      valueList.addAtEnd(v);
    }

    return valueList;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterable<Double> toIterable(final double[] values) {
    // Asserts that the given values is not null.
    if (values == null) {
      throw new IllegalArgumentException("The given values is null.");
    }

    final SimpleLinkedList<Double> valueList = SimpleLinkedList.createEmpty();

    // Iterates the given values.
    for (final var v : values) {
      valueList.addAtEnd(v);
    }

    return valueList;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <E> Iterable<E> toIterable(final E[] elements) {
    // Asserts that the given elements is not null.
    if (elements == null) {
      throw new IllegalArgumentException("The given elements is null.");
    }

    final SimpleLinkedList<E> elementList = SimpleLinkedList.createEmpty();

    // Iterates the given elements.
    for (final var e : elements) {
      elementList.addAtEnd(e);
    }

    return elementList;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterable<Long> toIterable(final int[] values) {
    // Asserts that the given values is not null.
    if (values == null) {
      throw new IllegalArgumentException("The given values is null.");
    }

    final SimpleLinkedList<Long> valueList = SimpleLinkedList.createEmpty();

    // Iterates the given elements.
    for (final long v : values) {
      valueList.addAtEnd(v);
    }

    return valueList;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterable<Long> toIterable(final long[] values) {
    // Asserts that the given values is not null.
    if (values == null) {
      throw new IllegalArgumentException("The given values is null.");
    }

    final SimpleLinkedList<Long> valueList = SimpleLinkedList.createEmpty();

    // Iterates the given elements.
    for (final var v : values) {
      valueList.addAtEnd(v);
    }

    return valueList;
  }
}
