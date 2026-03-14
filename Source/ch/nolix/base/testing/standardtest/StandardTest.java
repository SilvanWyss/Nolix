/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.testing.standardtest;

import java.util.Optional;

import ch.nolix.base.errorcontrol.validator.ArgumentMediator;
import ch.nolix.base.errorcontrol.validator.BooleanMediator;
import ch.nolix.base.errorcontrol.validator.ByteMediator;
import ch.nolix.base.errorcontrol.validator.DoubleMediator;
import ch.nolix.base.errorcontrol.validator.IterableMediator;
import ch.nolix.base.errorcontrol.validator.LongMediator;
import ch.nolix.base.errorcontrol.validator.MultiDoubleMediator;
import ch.nolix.base.errorcontrol.validator.MultiLongMediator;
import ch.nolix.base.errorcontrol.validator.OptionalMediator;
import ch.nolix.base.errorcontrol.validator.StringMediator;
import ch.nolix.baseapi.errorcontrol.validation.IOptionalMediator;

/**
 * @author Silvan Wyss
 */
public abstract class StandardTest { //NOSONAR: StandardTest does not have abstract methods.

  /**
   * @param argument
   * @return a new {@link BooleanMediator} for the given argument.
   */
  protected static final BooleanMediator expect(final boolean argument) {
    return BooleanMediator.forArgument(argument);
  }

  /**
   * @param argument
   * @return a new {@link ByteMediator} for the given argument.
   */
  protected static final ByteMediator expect(final byte argument) {
    return new ByteMediator(argument);
  }

  /**
   * @param argument
   * @return a new double mediator that belongs to this test and is for the given
   *         argument.
   */
  protected static final DoubleMediator expect(final double argument) {
    return DoubleMediator.forArgument(argument);
  }

  /**
   * @param argument
   * @return a new double mediator that belongs to this test and is for the given
   *         argument.
   */
  protected static final DoubleMediator expect(final Double argument) {
    return DoubleMediator.forArgument(argument);
  }

  /**
   * @param argument
   * @return a new long mediator that belongs to this test and is for the given
   *         argument.
   */
  protected static final LongMediator expect(final int argument) {
    return LongMediator.forArgument(argument);
  }

  /**
   * @param argument
   * @return a new long mediator that belongs to this test and is for the given
   *         argument.
   */
  protected static final LongMediator expect(final Integer argument) {
    return LongMediator.forArgument(argument);
  }

  /**
   * @param container
   * @param <E>       is the type of the elements of the given container.
   * @return a new container mediator that belongs to this test and is for the
   *         given container.
   */
  protected static final <E> IterableMediator<E> expect(final Iterable<E> container) {
    return new IterableMediator<>(container);
  }

  /**
   * @param argument
   * @return a new long mediator that belongs to this test and is for the given
   *         argument.
   */
  protected static final LongMediator expect(final long argument) {
    return LongMediator.forArgument(argument);
  }

  /**
   * @param argument
   * @return a new long mediator that belongs to this test and is for the given
   *         argument.
   */
  protected static final LongMediator expect(final Long argument) {
    return LongMediator.forArgument(argument);
  }

  /**
   * @param argument
   * @param <T>      is the type of the element of the given {@link Optional}
   *                 argument.
   * @return a new {@link IOptionalMediator} for the given argument.
   */
  protected static final <T> IOptionalMediator expect(
    final Optional<T> argument //NOSONAR: An Optional is the argument of an OptionalMediator.
  ) {
    return OptionalMediator.forArgument(argument);
  }

  /**
   * @param argument
   * @param <V>      is the type of the given argument.
   * @return a new object mediator that belongs to this test and has the given
   *         argument.
   */
  protected static final <V> ArgumentMediator<V> expect(final V argument) {
    return ArgumentMediator.forArgument(argument);
  }

  /**
   * @param argument
   * @return a new string mediator that belongs to this nolix test and has the
   *         given argument.
   */
  protected static final StringMediator expect(final String argument) {
    return StringMediator.forArgument(argument);
  }

  /**
   * @param closure
   * @return a new closure mediator that belongs for the given closure.
   */
  protected static final ClosureMediator expectRunning(final Runnable closure) {
    return ClosureMediator.forClosure(closure);
  }

  /**
   * @param arguments
   * @return a new multi double mediator that belongs to this test and is for the
   *         given arguments.
   */
  protected static final MultiDoubleMediator expectTheDoubles(final double... arguments) {
    return new MultiDoubleMediator(arguments);
  }

  /**
   * @param arguments
   * @return a new multi double mediator that belongs to this test and is for the
   *         given arguments.
   */
  protected static final MultiDoubleMediator expectTheDoubles(Iterable<Double> arguments) {
    return new MultiDoubleMediator(arguments);
  }

  /**
   * @param ints
   * @return a new {@link MultiLongMediator} for the given ints.
   * @throws RuntimeException if the given ints is null.
   */
  protected static final MultiLongMediator expectTheInts(final int... ints) {
    return MultiLongMediator.forArguments(ints);
  }

  /**
   * @param longs
   * @return a new {@link MultiLongMediator} for the given longs.
   * @throws RuntimeException if the given longs is null.
   */
  protected static final MultiLongMediator expectTheLongs(final Iterable<Long> longs) {
    return MultiLongMediator.forArguments(longs);
  }

  /**
   * @param argument
   * @param arguments
   * @return a new long container mediator that belongs to this test and has the
   *         given arguments.
   */
  protected static final MultiLongMediator expectTheLongs(final long argument, final long... arguments) {
    return expectTheLongs(argument, arguments);
  }
}
