package ch.nolix.core.testing.standardtest;

import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.core.errorcontrol.validator.ArgumentMediator;
import ch.nolix.core.errorcontrol.validator.ByteMediator;
import ch.nolix.core.errorcontrol.validator.DoubleMediator;
import ch.nolix.core.errorcontrol.validator.IterableMediator;
import ch.nolix.core.errorcontrol.validator.LongMediator;
import ch.nolix.core.errorcontrol.validator.MultiDoubleMediator;
import ch.nolix.core.errorcontrol.validator.MultiLongMediator;
import ch.nolix.core.errorcontrol.validator.StringMediator;

/**
 * @author Silvan Wyss
 * @version 2016-09-01
 */
public abstract class StandardTest { //NOSONAR: StandardTest does not have abstract methods.

  /**
   * Generates an error if the given value is false.
   * 
   * @param value
   */
  protected static final void expect(final boolean value) {

    //Handles the case that the given value is false.
    if (!value) {
      throw GeneralException.withErrorMessage("True was expected, but false was received.");
    }
  }

  /**
   * Generates an error for all of the given values that are false.
   * 
   * @param value
   * @param values
   */
  protected static final void expect(final boolean value, final boolean... values) {

    //Handles the case that the given values is null.
    if (values == null) {
      throw GeneralException.withErrorMessage("True values were expected, but null was received.");
    }

    if (!value) {
      throw GeneralException.withErrorMessage("True values were expected, but the 1th value is false.");
    }

    //Handles the case that the given values is not null.
    var index = 2;
    for (final boolean b : values) {

      //Handles the case that the current value is false.
      if (!b) {
        throw GeneralException.withErrorMessage("True values were expected, but the " + index + "th value is false.");
      }

      //Increments index.
      index++;
    }
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
   * Generates an error if the given value is true.
   * 
   * @param value
   */
  protected static final void expectNot(final boolean value) {

    //Handles the case that the given value is true.
    if (value) {
      throw GeneralException.withErrorMessage("False was expected, but true was received.");
    }
  }

  /**
   * Generates an error for all of the given values that are true.
   * 
   * @param value
   * @param values
   */
  protected static final void expectNot(final boolean value, final boolean... values) {

    //Handles the case that the given values is null.
    if (values == null) {
      throw GeneralException.withErrorMessage("False values were expected, but null was received.");
    }

    //Handles the case that the given value is false.
    if (value) {
      throw GeneralException.withErrorMessage("False values were expected, but the 1th value is true.");
    }

    //Handles the case that the given values is not null.
    var index = 2;
    for (final boolean v : values) {

      //Handles the case that the current value is false.
      if (v) {
        throw GeneralException.withErrorMessage("False values were expected, but the " + index + "th value is true.");
      }

      //Increments index.
      index++;
    }
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
