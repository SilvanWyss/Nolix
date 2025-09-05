package ch.nolix.core.independent.arraytool;

import ch.nolix.core.independent.list.List;
import ch.nolix.coreapi.independent.arraytool.IArrayTool;

/**
 * @author Silvan Wyss
 * @version 2017-08-14
 */
public final class ArrayTool implements IArrayTool {
  /**
   * {@inheritDoc}
   */
  @Override
  public <E> E[] createArrayWithElement(
    final E element,
    final @SuppressWarnings("unchecked") E... elements) {
    final @SuppressWarnings("unchecked") var array = (E[]) new Object[elements.length + 1];
    array[0] = element;
    System.arraycopy(elements, 0, array, 1, elements.length);

    return array;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterable<Byte> createIterable(final byte[] values) {
    //Asserts that the given values is not null.
    if (values == null) {
      throw new IllegalArgumentException("The given values is null.");
    }

    final var valueList = new List<Byte>();

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

    final List<Double> valueList = new List<>();

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

    final var elementList = new List<E>();

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

    final var valueList = new List<Long>();

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

    final var valueList = new List<Long>();

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
        stringBuilder.append(values[i] + ", ");
      } else {
        stringBuilder.append(values[i]);
      }
    }

    return stringBuilder.toString();
  }
}
