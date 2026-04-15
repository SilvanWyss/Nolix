/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.independent.arraytool;

import ch.nolix.base.independent.list.List;
import ch.nolix.baseapi.independent.arraytool.IArrayTool;

/**
 * @author Silvan Wyss
 */
public final class ArrayTool implements IArrayTool {
  /**
   * {@inheritDoc}
   */
  @Override
  public Iterable<Byte> createIterable(final byte[] values) {
    //Asserts that the given values is not null.
    if (values == null) {
      throw new IllegalArgumentException("The given values is null.");
    }

    final List<Byte> valueList = List.createEmpty();

    //Iterates the given elements.
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
    //Asserts that the given values is not null.
    if (values == null) {
      throw new IllegalArgumentException("The given values is null.");
    }

    final List<Double> valueList = List.createEmpty();

    //Iterates the given values.
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
    //Asserts that the given elements is not null.
    if (elements == null) {
      throw new IllegalArgumentException("The given elements is null.");
    }

    final List<E> elementList = List.createEmpty();

    //Iterates the given elements.
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
    //Asserts that the given values is not null.
    if (values == null) {
      throw new IllegalArgumentException("The given values is null.");
    }

    final List<Long> valueList = List.createEmpty();

    //Iterates the given elements.
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
    //Asserts that the given values is not null.
    if (values == null) {
      throw new IllegalArgumentException("The given values is null.");
    }

    final List<Long> valueList = List.createEmpty();

    //Iterates the given elements.
    for (final var v : values) {
      valueList.addAtEnd(v);
    }

    return valueList;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createString(final long[] values) {
    //Asserts that the given values is not null.
    if (values == null) {
      throw new IllegalArgumentException("The given values is null.");
    }

    final var stringBuilder = new StringBuilder();

    //Iterates the given values.
    for (var i = 0; i < values.length; i++) {
      if (i < values.length - 1) {
        stringBuilder.append(values[i]).append(", ");
      } else {
        stringBuilder.append(values[i]);
      }
    }

    return stringBuilder.toString();
  }
}
