/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.validator;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Optional;

import ch.nolix.base.foundation.iterablemapper.SimpleIterableMapper;
import ch.nolix.base.validation.multi.MultiArgumentMediator;
import ch.nolix.base.validation.multi.MultiDoubleMediator;
import ch.nolix.base.validation.multi.MultiLongMediator;
import ch.nolix.base.validation.multi.MultiStringMediator;
import ch.nolix.base.validation.object.NamableMethodMediator;
import ch.nolix.base.validation.object.ExtendedOptionalMediator;
import ch.nolix.base.validation.object.NamableStringMediator;
import ch.nolix.base.validation.object.ExtendedTypeMediator;
import ch.nolix.base.validation.object.NamableBigDecimalMediator;
import ch.nolix.base.validation.object.NamableIterableMediator;
import ch.nolix.base.validation.object.NamableObjectMediator;
import ch.nolix.base.validation.primitive.NamableBitMediator;
import ch.nolix.base.validation.primitive.NamableByteMediator;
import ch.nolix.base.validation.primitive.NamableDoubleMediator;
import ch.nolix.base.validation.primitive.NamableLongMediator;

/**
 * The {@link Validator} provides methods to validate arguments.
 * 
 * Of the {@link Validator} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class Validator { // NOSONAR: The GlobalValidator bundles all validation methods.

  private static final SimpleIterableMapper ITERABLE_MAPPER = new SimpleIterableMapper();

  /**
   * Prevents that an instance of the {@link Validator} can be created.
   */
  private Validator() {
  }

  /**
   * @param argument
   * @param <A>      the type of the given argument
   * @return a new {@link NamableObjectMediator} for given argument.
   */
  public static <A> NamableObjectMediator<A> assertThat(final A argument) {
    return NamableObjectMediator.forArgument(argument);
  }

  /**
   * @param argument
   * @return a new {@link NamableBigDecimalMediator} for the given argument.
   */
  public static NamableBigDecimalMediator assertThat(final BigDecimal argument) {
    return NamableBigDecimalMediator.forArgument(argument);
  }

  /**
   * @param argument
   * @return a new {@link NamableByteMediator} for the given argument.
   */
  public static NamableByteMediator assertThat(final byte argument) {
    return NamableByteMediator.forArgument(argument);
  }

  /**
   * @param argument
   * @return a new {@link NamableIterableMediator} for the given argument.
   */
  public static NamableIterableMediator<Byte> assertThat(final byte[] argument) {
    // Handles the case that the given argument is null.
    if (argument == null) {
      NamableIterableMediator.forArgument(null);
    }

    // Handles the case that the given argument is not null.
    return NamableIterableMediator.forArgument(ITERABLE_MAPPER.toIterable(argument));
  }

  /**
   * @param argument
   * @param <T>      the type of the given argument
   * @return a new {@link ExtendedTypeMediator} for the given argument.
   */
  public static <T> ExtendedTypeMediator<T> assertThat(final Class<T> argument) {
    return ExtendedTypeMediator.forArgument(argument);
  }

  /**
   * @param argument
   * @return a new {@link NamableDoubleMediator} for the given argument.
   */
  public static NamableDoubleMediator assertThat(final double argument) {
    return NamableDoubleMediator.forArgument(argument);
  }

  /**
   * @param argument
   * @return a new {@link NamableIterableMediator} for the given argument.
   */
  public static NamableIterableMediator<Double> assertThat(final double[] argument) {
    // Handles the case that the given argument is null.
    if (argument == null) {
      return NamableIterableMediator.forArgument(null);
    }

    // Handles the case that the given argument is not null.
    return NamableIterableMediator.forArgument(ITERABLE_MAPPER.toIterable(argument));
  }

  /**
   * @param argument
   * @return a new {@link NamableLongMediator} for the given argument.
   */
  public static NamableLongMediator assertThat(final int argument) {
    return NamableLongMediator.forArgument(argument);
  }

  /**
   * @param argument
   * @return a new {@link NamableIterableMediator} for the given argument.
   */
  public static NamableIterableMediator<Long> assertThat(final int[] argument) {
    // Handles the case that the given argument is null.
    if (argument == null) {
      return NamableIterableMediator.forArgument(null);
    }

    // Handles the case that the given argument is not null.
    return NamableIterableMediator.forArgument(ITERABLE_MAPPER.toIterable(argument));
  }

  /**
   * @param argument
   * @param <A>      the type of the elements of the given argument
   * @return a new {@link NamableIterableMediator} for the given argument.
   */
  public static <A> NamableIterableMediator<A> assertThat(final Iterable<A> argument) {
    return NamableIterableMediator.forArgument(argument);
  }

  /**
   * @param argument
   * @return a new {@link NamableLongMediator} for the given argument.
   */
  public static NamableLongMediator assertThat(final long argument) {
    return NamableLongMediator.forArgument(argument);
  }

  /**
   * @param argument
   * @return a new {@link NamableIterableMediator} for the given argument.
   */
  public static NamableIterableMediator<Long> assertThat(final long[] argument) {
    // Handles the case that the given argument is null.
    if (argument == null) {
      return NamableIterableMediator.forArgument(null);
    }

    // Handles the case that the given argument is not null.
    return NamableIterableMediator.forArgument(ITERABLE_MAPPER.toIterable(argument));
  }

  /**
   * @param argument
   * @return a new {@link NamableMethodMediator} for the given argument.
   */
  public static NamableMethodMediator assertThat(final Method argument) {
    return NamableMethodMediator.forArgument(argument);
  }

  /**
   * @param argument
   * @param <T>      the type of the element of the given {@link Optional}
   *                 argument
   * @return a new {@link ExtendedOptionalMediator} for the given argument.
   */
  public static <T> ExtendedOptionalMediator<T> assertThat(
    final Optional<T> argument // NOSONAR: An Optional is the argument of an ExtendedOptionalMediator.
  ) {
    return ExtendedOptionalMediator.forArgument(argument);
  }

  /**
   * @param argument
   * @return a new {@link NamableStringMediator} for the given argument.
   */
  public static NamableStringMediator assertThat(final String argument) {
    return NamableStringMediator.forArgument(argument);
  }

  /**
   * @param argument
   * @return a new {@link NamableBitMediator} for the given argument.
   */
  public static NamableBitMediator assertThatTheBit(final boolean argument) {
    return NamableBitMediator.forArgument(argument);
  }

  /**
   * @param arguments
   * @return a new {@link MultiDoubleMediator} for the given arguments
   * @throws RuntimeException if the given arguments is null
   */
  public static MultiDoubleMediator assertThatTheDoubles(final Iterable<Double> arguments) {
    return MultiDoubleMediator.forArugments(arguments);
  }

  /**
   * @param arguments
   * @param <E>       the type of the given arguments
   * @return a new {@link MultiArgumentMediator} for the given arguments
   * @throws RuntimeException if the given arguments is null
   */
  public static <E> MultiArgumentMediator<E> assertThatTheElements(final E[] arguments) {
    return MultiArgumentMediator.forArguments(arguments);
  }

  /**
   * @param arguments
   * @param <E>       the type of the given arguments
   * @return a new {@link MultiArgumentMediator} for the given arguments
   * @throws RuntimeException if the given arguments is null
   */
  public static <E> MultiArgumentMediator<E> assertThatTheElements(final Iterable<E> arguments) {
    return MultiArgumentMediator.forArguments(arguments);
  }

  /**
   * @param longs
   * @return a new {@link MultiLongMediator} for the given longs
   * @throws RuntimeException if the given longs is null
   */
  public static MultiLongMediator assertThatTheLongs(final Iterable<Long> longs) {
    return MultiLongMediator.forArguments(longs);
  }

  /**
   * @param arguments
   * @return a new {@link MultiStringMediator} for the given arguments
   * @throws RuntimeException if the given arguments is null
   */
  public static MultiStringMediator assertThatTheStrings(final Iterable<String> arguments) {
    return MultiStringMediator.forArguments(arguments);
  }
}
