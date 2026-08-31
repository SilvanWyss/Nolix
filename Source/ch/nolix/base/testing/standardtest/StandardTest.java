/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.testing.standardtest;

import java.util.Optional;

import ch.nolix.base.validation.multi.MultiDoubleMediator;
import ch.nolix.base.validation.multi.MultiLongMediator;
import ch.nolix.base.validation.object.NamableIterableMediator;
import ch.nolix.base.validation.object.NamableObjectMediator;
import ch.nolix.base.validation.object.NamableStringMediator;
import ch.nolix.base.validation.object.OptionalMediator;
import ch.nolix.base.validation.primitive.BooleanMediator;
import ch.nolix.base.validation.primitive.ByteMediator;
import ch.nolix.base.validation.primitive.LongMediator;
import ch.nolix.base.validation.primitive.NamableDoubleMediator;
import ch.nolix.baseapi.validation.object.IOptionalMediator;

/**
 * @author Silvan Wyss
 */
public abstract class StandardTest { // NOSONAR: StandardTest does not have abstract methods.

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
    return ByteMediator.forArgument(argument);
  }

  /**
   * @param argument
   * @return a new double mediator that belongs to this test and is for the given
   *         argument.
   */
  protected static final NamableDoubleMediator expect(final double argument) {
    return NamableDoubleMediator.forArgument(argument);
  }

  /**
   * @param argument
   * @return a new double mediator that belongs to this test and is for the given
   *         argument.
   */
  protected static final NamableDoubleMediator expect(final Double argument) {
    return NamableDoubleMediator.forArgument(argument);
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
   * @param argument
   * @param <E>      the type of the elements of the given argument
   * @return a new {@link NamableIterableMediator} for the given argument.
   */
  protected static final <E> NamableIterableMediator<E> expect(final Iterable<E> argument) {
    return NamableIterableMediator.forArgument(argument);
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
   * @param <T>      the type of the element of the given {@link Optional}
   *                 argument
   * @return a new {@link IOptionalMediator} for the given argument.
   */
  protected static final <T> IOptionalMediator expect(
    final Optional<T> argument // NOSONAR: An Optional is the argument of an OptionalMediator.
  ) {
    return OptionalMediator.forArgument(argument);
  }

  /**
   * @param argument
   * @param <A>      the type of the given argument
   * @return a new {@link NamableObjectMediator} for the given argument.
   */
  protected static final <A> NamableObjectMediator<A> expect(final A argument) {
    return NamableObjectMediator.forArgument(argument);
  }

  /**
   * @param argument
   * @return a new string mediator that belongs to this nolix test and has the
   *         given argument.
   */
  protected static final NamableStringMediator expect(final String argument) {
    return NamableStringMediator.forArgument(argument);
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
    return MultiDoubleMediator.forArugments(arguments);
  }

  /**
   * @param arguments
   * @return a new multi double mediator that belongs to this test and is for the
   *         given arguments.
   */
  protected static final MultiDoubleMediator expectTheDoubles(Iterable<Double> arguments) {
    return MultiDoubleMediator.forArugments(arguments);
  }

  /**
   * @param ints
   * @return a new {@link MultiLongMediator} for the given ints
   * @throws RuntimeException if the given ints is null
   */
  protected static final MultiLongMediator expectTheInts(final int... ints) {
    return MultiLongMediator.forArguments(ints);
  }

  /**
   * @param longs
   * @return a new {@link MultiLongMediator} for the given longs
   * @throws RuntimeException if the given longs is null
   */
  protected static final MultiLongMediator expectTheLongs(final Iterable<Long> longs) {
    return MultiLongMediator.forArguments(longs);
  }

  /**
   * @param arguments
   * @return a new {@link MultiLongMediator} for the given arguments.
   */
  protected static final MultiLongMediator expectTheLongs(final long... arguments) {
    return MultiLongMediator.forArguments(arguments);
  }
}
