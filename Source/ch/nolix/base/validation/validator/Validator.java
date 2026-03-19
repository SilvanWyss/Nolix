/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.validator;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Optional;

import ch.nolix.base.independent.arraytool.ArrayTool;
import ch.nolix.base.validation.multi.MultiArgumentMediator;
import ch.nolix.base.validation.multi.MultiDoubleMediator;
import ch.nolix.base.validation.multi.MultiLongMediator;
import ch.nolix.base.validation.multi.MultiStringMediator;
import ch.nolix.base.validation.object.ExtendedArgumentMediator;
import ch.nolix.base.validation.object.ExtendedBigDecimalMediator;
import ch.nolix.base.validation.object.ExtendedIterableMediator;
import ch.nolix.base.validation.object.ExtendedMethodMediator;
import ch.nolix.base.validation.object.ExtendedOptionalMediator;
import ch.nolix.base.validation.object.ExtendedStringMediator;
import ch.nolix.base.validation.object.ExtendedTypeMediator;
import ch.nolix.base.validation.primitive.ExtendedBitMediator;
import ch.nolix.base.validation.primitive.ExtendedByteMediator;
import ch.nolix.base.validation.primitive.ExtendedDoubleMediator;
import ch.nolix.base.validation.primitive.NamableLongMediator;

/**
 * The {@link Validator} provides methods to validate arguments.
 * 
 * Of the {@link Validator} an instance cannot be created.
 * 
 * @author Silvan Wyss
 */
public final class Validator { //NOSONAR: The GlobalValidator bundles all validation methods.

  private static final ArrayTool ARRAY_TOOL = new ArrayTool();

  /**
   * Prevents that an instance of the {@link Validator} can be created.
   */
  private Validator() {
  }

  /**
   * @param argument
   * @param <A>      is the type of the given argument.
   * @return a new {@link ExtendedArgumentMediator} for given argument.
   */
  public static <A> ExtendedArgumentMediator<A> assertThat(final A argument) {
    return new ExtendedArgumentMediator<>(argument);
  }

  /**
   * @param argument
   * @return a new {@link ExtendedBigDecimalMediator} for the given argument.
   */
  public static ExtendedBigDecimalMediator assertThat(final BigDecimal argument) {
    return new ExtendedBigDecimalMediator(argument);
  }

  /**
   * @param argument
   * @return a new {@link ExtendedByteMediator} for the given argument.
   */
  public static ExtendedByteMediator assertThat(final byte argument) {
    return new ExtendedByteMediator(argument);
  }

  /**
   * @param argument
   * @return a new {@link ExtendedIterableMediator} for the given argument.
   */
  public static ExtendedIterableMediator<Byte> assertThat(final byte[] argument) {
    return new ExtendedIterableMediator<>(ARRAY_TOOL.createIterable(argument));
  }

  /**
   * @param argument
   * @param <T>      is the type of the given argument
   * @return a new {@link ExtendedTypeMediator} for the given argument.
   */
  public static <T> ExtendedTypeMediator<T> assertThat(final Class<T> argument) {
    return ExtendedTypeMediator.forArgument(argument);
  }

  /**
   * @param argument
   * @return a new {@link ExtendedDoubleMediator} for the given argument.
   */
  public static ExtendedDoubleMediator assertThat(final double argument) {
    return new ExtendedDoubleMediator(argument);
  }

  /**
   * @param argument
   * @return a new {@link ExtendedIterableMediator} for the given argument.
   */
  public static ExtendedIterableMediator<Double> assertThat(final double[] argument) {
    //Handles the case that the given argument is null.
    if (argument == null) {
      return new ExtendedIterableMediator<>(null);
    }

    //Handles the case that the given argument is not null.
    return new ExtendedIterableMediator<>(ARRAY_TOOL.createIterable(argument));
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
   * @return a new {@link ExtendedIterableMediator} for the given argument.
   */
  public static ExtendedIterableMediator<Long> assertThat(final int[] argument) {
    //Handles the case that the given argument is null.
    if (argument == null) {
      return new ExtendedIterableMediator<>(null);
    }

    //Handles the case that the given argument is not null.
    return new ExtendedIterableMediator<>(ARRAY_TOOL.createIterable(argument));
  }

  /**
   * @param argument
   * @param <A>      is the type of the elements of the given argument.
   * @return a new {@link ExtendedIterableMediator} for the given argument.
   */
  public static <A> ExtendedIterableMediator<A> assertThat(final Iterable<A> argument) {
    return new ExtendedIterableMediator<>(argument);
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
   * @return a new {@link ExtendedIterableMediator} for the given argument.
   */
  public static ExtendedIterableMediator<Long> assertThat(final long[] argument) {
    //Handles the case that the given argument is null.
    if (argument == null) {
      return new ExtendedIterableMediator<>(null);
    }

    //Handles the case that the given argument is not null.
    return new ExtendedIterableMediator<>(ARRAY_TOOL.createIterable(argument));
  }

  /**
   * @param argument
   * @return a new {@link ExtendedMethodMediator} for the given argument.
   */
  public static ExtendedMethodMediator assertThat(final Method argument) {
    return ExtendedMethodMediator.forArgument(argument);
  }

  /**
   * @param argument
   * @param <T>      is the type of the element of the given {@link Optional}
   *                 argument.
   * @return a new {@link ExtendedOptionalMediator} for the given argument.
   */
  public static <T> ExtendedOptionalMediator<T> assertThat(
    final Optional<T> argument //NOSONAR: An Optional is the argument of an ExtendedOptionalMediator.
  ) {
    return ExtendedOptionalMediator.forArgument(argument);
  }

  /**
   * @param argument
   * @return a new {@link ExtendedStringMediator} for the given argument.
   */
  public static ExtendedStringMediator assertThat(final String argument) {
    return ExtendedStringMediator.forArgument(argument);
  }

  /**
   * @param argument
   * @return a new {@link ExtendedBitMediator} for the given argument.
   */
  public static ExtendedBitMediator assertThatTheBit(final boolean argument) {
    return new ExtendedBitMediator(argument);
  }

  /**
   * @param arguments
   * @return a new {@link MultiDoubleMediator} for the given arguments.
   * @throws RuntimeException if the given arguments is null.
   */
  public static MultiDoubleMediator assertThatTheDoubles(final Iterable<Double> arguments) {
    return MultiDoubleMediator.forArugments(arguments);
  }

  /**
   * @param arguments
   * @param <E>       is the type of the given arguments.
   * @return a new {@link MultiArgumentMediator} for the given arguments.
   * @throws RuntimeException if the given arguments is null.
   */
  public static <E> MultiArgumentMediator<E> assertThatTheElements(final E[] arguments) {
    return MultiArgumentMediator.forArguments(arguments);
  }

  /**
   * @param arguments
   * @param <E>       is the type of the given arguments.
   * @return a new {@link MultiArgumentMediator} for the given arguments.
   * @throws RuntimeException if the given arguments is null.
   */
  public static <E> MultiArgumentMediator<E> assertThatTheElements(final Iterable<E> arguments) {
    return MultiArgumentMediator.forArguments(arguments);
  }

  /**
   * @param longs
   * @return a new {@link MultiLongMediator} for the given longs.
   * @throws RuntimeException if the given longs is null.
   */
  public static MultiLongMediator assertThatTheLongs(final Iterable<Long> longs) {
    return MultiLongMediator.forArguments(longs);
  }

  /**
   * @param arguments
   * @return a new {@link MultiStringMediator} for the given arguments.
   * @throws RuntimeException if the given arguments is null.
   */
  public static MultiStringMediator assertThatTheStrings(final Iterable<String> arguments) {
    return MultiStringMediator.forArguments(arguments);
  }
}
