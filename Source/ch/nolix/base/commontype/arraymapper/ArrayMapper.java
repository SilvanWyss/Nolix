/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.commontype.arraymapper;

import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;

import ch.nolix.baseapi.commontype.arraymapper.IArrayMapper;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.UnequalArgumentException;
import ch.nolix.baseapi.foundation.function.ToByteFunction;

/**
 * @author Silvan Wyss
 */
public final class ArrayMapper implements IArrayMapper {
  /**
   * {@inheritDoc}
   */
  @Override
  public <E> byte[] toByteArray(Iterable<E> iterable, int n, ToByteFunction<E> byteMapper) {
    if (byteMapper == null) {
      throw ArgumentIsNullException.forArgumentName("byte mapper");
    }

    if (iterable != null) {
      final var array = new byte[n];
      final var iterator = iterable.iterator();

      for (var i = 0; i < n; i++) {
        final var element = iterator.next();

        if (element == null) {
          array[i] = 0;
        } else {
          array[i] = byteMapper.mapElementToByte(element);
        }
      }

      return array;
    }

    if (n != 0) {
      throw UnequalArgumentException.forArgumentAndArgumentNameAndValue(n, "n", 0);
    }

    return new byte[0];
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <E> double[] toDoubleArray(final Iterable<E> iterable, final int n, final ToDoubleFunction<E> doubleMapper) {
    if (doubleMapper == null) {
      throw ArgumentIsNullException.forArgumentName("double mapper");
    }

    if (iterable != null) {
      final var array = new double[n];
      final var iterator = iterable.iterator();

      for (var i = 0; i < n; i++) {
        final var element = iterator.next();

        if (element == null) {
          array[i] = 0;
        } else {
          array[i] = doubleMapper.applyAsDouble(element);
        }
      }

      return array;
    }

    if (n != 0) {
      throw UnequalArgumentException.forArgumentAndArgumentNameAndValue(n, "n", 0);
    }

    return new double[0];
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <E> int[] toIntArray(final Iterable<E> iterable, final int n, final ToIntFunction<E> intMapper) {
    if (intMapper == null) {
      throw ArgumentIsNullException.forArgumentName("int mapper");
    }

    if (iterable != null) {
      final var array = new int[n];
      final var iterator = iterable.iterator();

      for (var i = 0; i < n; i++) {
        final var element = iterator.next();

        if (element == null) {
          array[i] = 0;
        } else {
          array[i] = intMapper.applyAsInt(element);
        }
      }

      return array;
    }

    if (n != 0) {
      throw UnequalArgumentException.forArgumentAndArgumentNameAndValue(n, "n", 0);
    }

    return new int[0];
  }
}
