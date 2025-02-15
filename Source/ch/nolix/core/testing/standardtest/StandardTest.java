package ch.nolix.core.testing.standardtest;

import java.util.Optional;

import ch.nolix.core.errorcontrol.validator.ArgumentMediator;
import ch.nolix.core.errorcontrol.validator.BooleanMediator;
import ch.nolix.core.errorcontrol.validator.ByteMediator;
import ch.nolix.core.errorcontrol.validator.DoubleMediator;
import ch.nolix.core.errorcontrol.validator.IterableMediator;
import ch.nolix.core.errorcontrol.validator.LongMediator;
import ch.nolix.core.errorcontrol.validator.MultiDoubleMediator;
import ch.nolix.core.errorcontrol.validator.MultiLongMediator;
import ch.nolix.core.errorcontrol.validator.OptionalMediator;
import ch.nolix.core.errorcontrol.validator.StringMediator;
import ch.nolix.coreapi.errorcontrolapi.validatorapi.IOptionalMediator;

/**
 * @author Silvan Wyss
 * @version 2016-09-01
 */
public abstract class StandardTest { //NOSONAR: StandardTest does not have abstract methods.

  /**
   * @param value
   * @return a new {@link BooleanMediator} for the given value.
   */
  protected static final BooleanMediator expect(final boolean value) {
    return BooleanMediator.forArgument(value);
  }

  /**
   * @param value
   * @return a new {@link ByteMediator} for the given value.
   */
  protected static final ByteMediator expect(final byte value) {
    return new ByteMediator(value);
  }

  /**
   * @param value
   * @return a new double mediator that belongs to this test and is for the given
   *         value.
   */
  protected static final DoubleMediator expect(final double value) {
    return DoubleMediator.forArgument(value);
  }

  /**
   * @param value
   * @return a new double mediator that belongs to this test and is for the given
   *         value.
   */
  protected static final DoubleMediator expect(final Double value) {
    return DoubleMediator.forArgument(value);
  }

  /**
   * @param value
   * @return a new long mediator that belongs to this test and is for the given
   *         value.
   */
  protected static final LongMediator expect(final int value) {
    return LongMediator.forArgument(value);
  }

  /**
   * @param value
   * @return a new long mediator that belongs to this test and is for the given
   *         value.
   */
  protected static final LongMediator expect(final Integer value) {
    return LongMediator.forArgument(value);
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
   * @param value
   * @return a new long mediator that belongs to this test and is for the given
   *         value.
   */
  protected static final LongMediator expect(final long value) {
    return LongMediator.forArgument(value);
  }

  /**
   * @param value
   * @return a new long mediator that belongs to this test and is for the given
   *         value.
   */
  protected static final LongMediator expect(final Long value) {
    return LongMediator.forArgument(value);
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
   * @param value
   * @param <V>   is the type of the given value.
   * @return a new object mediator that belongs to this test and has the given
   *         value.
   */
  protected static final <V> ArgumentMediator<V> expect(final V value) {
    return ArgumentMediator.forArgument(value);
  }

  /**
   * @param value
   * @return a new string mediator that belongs to this nolix test and has the
   *         given value.
   */
  protected static final StringMediator expect(final String value) {
    return StringMediator.forArgument(value);
  }

  /**
   * @param closure
   * @return a new closure mediator that belongs for the given closure.
   */
  protected static final ClosureMediator expectRunning(final Runnable closure) {
    return new ClosureMediator(closure);
  }

  /**
   * @param values
   * @return a new multi double mediator that belongs to this test and is for the
   *         given values.
   */
  protected static final MultiDoubleMediator expectTheDoubles(final double... values) {
    return new MultiDoubleMediator(values);
  }

  /**
   * @param values
   * @return a new multi double mediator that belongs to this test and is for the
   *         given values.
   */
  protected static final MultiDoubleMediator expectTheDoubles(Iterable<Double> values) {
    return new MultiDoubleMediator(values);
  }

  /**
   * @param value
   * @param values
   * @return a new multi long mediator that belongs to this test and is for the
   *         given values.
   */
  protected static final MultiLongMediator expectTheInts(final int value, final int... values) {
    return MultiLongMediator.forValue(value, values);
  }

  /**
   * @param values
   * @return a new long container mediator that belongs to this test and has the
   *         given values.
   */
  protected static final MultiLongMediator expectTheLongs(final Iterable<Long> values) {
    return new MultiLongMediator(values);
  }

  /**
   * @param value
   * @param values
   * @return a new long container mediator that belongs to this test and has the
   *         given values.
   */
  protected static final MultiLongMediator expectTheLongs(final long value, final long... values) {
    return expectTheLongs(value, values);
  }
}
