/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.foundation.arraytool;

import ch.nolix.base.foundation.linkedlist.SimpleLinkedList;
import ch.nolix.baseapi.foundation.arraytool.IArrayTool;

/**
 * @author Silvan Wyss
 */
public final class SimpleArrayTool implements IArrayTool {
  /**
   * {@inheritDoc}
   */
  @Override
  public Iterable<Byte> createIterable(final byte[] values) {
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
  public Iterable<Double> createIterable(final double[] values) {
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
  public <E> Iterable<E> createIterable(final E[] elements) {
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
  public Iterable<Long> createIterable(final int[] values) {
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
  public Iterable<Long> createIterable(final long[] values) {
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
