package ch.nolix.core.errorcontrol.validator;

import java.lang.reflect.Method;
import java.math.BigDecimal;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.independent.containertool.ArrayTool;

/**
 * The {@link GlobalValidator} provides methods to validate arguments. Of the
 * {@link GlobalValidator} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @version 2016-12-01
 */
public final class GlobalValidator { //NOSONAR: The GlobalValidator bundles all validation methods.

  private static final ArrayTool ARRAY_TOOL = new ArrayTool();

  /**
   * Prevents that an instance of the {@link GlobalValidator} can be created.
   */
  private GlobalValidator() {
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
   * @return a new {@link ExtendedLongMediator} for the given argument.
   */
  public static ExtendedLongMediator assertThat(final int argument) {
    return ExtendedLongMediator.forArgument(argument);
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
   * @return a new {@link ExtendedLongMediator} for the given argument.
   */
  public static ExtendedLongMediator assertThat(final long argument) {
    return ExtendedLongMediator.forArgument(argument);
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
    return new ExtendedMethodMediator(argument);
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
   * @throws ArgumentIsNullException if the given arguments is null.
   */
  public static MultiDoubleMediator assertThatTheDoubles(final Iterable<Double> arguments) {
    return new MultiDoubleMediator(arguments);
  }

  /**
   * @param arguments
   * @param <E>       is the type of the given arguments.
   * @return a new {@link MultiArgumentMediator} for the given arguments.
   * @throws ArgumentIsNullException if the given arguments is null.
   */
  public static <E> MultiArgumentMediator<E> assertThatTheElements(final E[] arguments) {
    return new MultiArgumentMediator<>(arguments);
  }

  /**
   * @param arguments
   * @param <E>       is the type of the given arguments.
   * @return a new {@link MultiArgumentMediator} for the given arguments.
   * @throws ArgumentIsNullException if the given arguments is null.
   */
  public static <E> MultiArgumentMediator<E> assertThatTheElements(final Iterable<E> arguments) {
    return new MultiArgumentMediator<>(arguments);
  }

  /**
   * @param arguments
   * @return a new {@link MultiLongMediator} for the given arguments.
   * @throws ArgumentIsNullException if the given arguments is null.
   */
  public static MultiLongMediator assertThatTheLongs(final Iterable<Long> arguments) {
    return new MultiLongMediator(arguments);
  }

  /**
   * @param arguments
   * @return a new {@link MultiStringMediator} for the given arguments.
   * @throws ArgumentIsNullException if the given arguments is null.
   */
  public static MultiStringMediator assertThatTheStrings(final Iterable<String> arguments) {
    return new MultiStringMediator(arguments);
  }
}
