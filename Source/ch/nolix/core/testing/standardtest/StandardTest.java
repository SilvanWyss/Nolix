//package declaration
package ch.nolix.core.testing.standardtest;

//own imports
import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.core.errorcontrol.validator.ArgumentMediator;
import ch.nolix.core.errorcontrol.validator.ByteMediator;
import ch.nolix.core.errorcontrol.validator.ContainerMediator;
import ch.nolix.core.errorcontrol.validator.DoubleMediator;
import ch.nolix.core.errorcontrol.validator.LongMediator;
import ch.nolix.core.errorcontrol.validator.MultiDoubleMediator;
import ch.nolix.core.errorcontrol.validator.MultiLongMediator;
import ch.nolix.core.errorcontrol.validator.StringMediator;

//class
/**
 * A test is a test that provides the fluent pattern for writing expectations.
 * 
 * @author Silvan Wyss
 * @version 2016-09-01
 */
public abstract class StandardTest { //NOSONAR: StandardTest does not have abstract methods.

  //method
  /**
   * Generates an error if the given value is false.
   * 
   * @param value
   */
  protected final void expect(final boolean value) {

    //Handles the case that the given value is false.
    if (!value) {
      throw GeneralException.withErrorMessage("True was expected, but false was received.");
    }
  }

  //method
  /**
   * Generates an error for all of the given values that are false.
   * 
   * @param value
   * @param values
   */
  protected final void expect(final boolean value, final boolean... values) {

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

  //method
  /**
   * @param value
   * @return a new {@link ByteMediator} for the given value.
   */
  protected final ByteMediator expect(final byte value) {
    return new ByteMediator(value);
  }

  //method
  /**
   * @param value
   * @return a new double mediator that belongs to this test and is for the given
   *         value.
   */
  protected final DoubleMediator expect(final double value) {
    return new DoubleMediator(value);
  }

  //method
  /**
   * @param value
   * @return a new double mediator that belongs to this test and is for the given
   *         value.
   */
  protected final DoubleMediator expect(final Double value) {
    return new DoubleMediator(value);
  }

  //method
  /**
   * @param value
   * @return a new long mediator that belongs to this test and is for the given
   *         value.
   */
  protected final LongMediator expect(final int value) {
    return LongMediator.forArgument(value);
  }

  //method
  /**
   * @param value
   * @return a new long mediator that belongs to this test and is for the given
   *         value.
   */
  protected final LongMediator expect(final Integer value) {
    return LongMediator.forArgument(value);
  }

  //method
  /**
   * @param container
   * @param <E>       is the type of the elements of the given container.
   * @return a new container mediator that belongs to this test and is for the
   *         given container.
   */
  protected final <E> ContainerMediator<E> expect(final Iterable<E> container) {
    return new ContainerMediator<>(container);
  }

  //method
  /**
   * @param value
   * @return a new long mediator that belongs to this test and is for the given
   *         value.
   */
  protected final LongMediator expect(final long value) {
    return LongMediator.forArgument(value);
  }

  //method
  /**
   * @param value
   * @return a new long mediator that belongs to this test and is for the given
   *         value.
   */
  protected final LongMediator expect(final Long value) {
    return LongMediator.forArgument(value);
  }

  //method
  /**
   * @param value
   * @param <V>   is the type of the given value.
   * @return a new object mediator that belongs to this test and has the given
   *         value.
   */
  protected final <V> ArgumentMediator<V> expect(final V value) {
    return new ArgumentMediator<>(value);
  }

  //method
  /**
   * @param value
   * @return a new string mediator that belongs to this nolix test and has the
   *         given value.
   */
  protected final StringMediator expect(final String value) {
    return new StringMediator(value);
  }

  //method
  /**
   * Generates an error if the given value is true.
   * 
   * @param value
   */
  protected final void expectNot(final boolean value) {

    //Handles the case that the given value is true.
    if (value) {
      throw GeneralException.withErrorMessage("False was expected, but true was received.");
    }
  }

  //method
  /**
   * Generates an error for all of the given values that are true.
   * 
   * @param value
   * @param values
   */
  protected final void expectNot(final boolean value, final boolean... values) {

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

  //method
  /**
   * @param closure
   * @return a new closure mediator that belongs for the given closure.
   */
  protected final ClosureMediator expectRunning(final Runnable closure) {
    return new ClosureMediator(closure);
  }

  //method
  /**
   * @param values
   * @return a new multi double mediator that belongs to this test and is for the
   *         given values.
   */
  protected final MultiDoubleMediator expectTheDoubles(final double... values) {
    return new MultiDoubleMediator(values);
  }

  //method
  /**
   * @param values
   * @return a new multi double mediator that belongs to this test and is for the
   *         given values.
   */
  protected final MultiDoubleMediator expectTheDoubles(Iterable<Double> values) {
    return new MultiDoubleMediator(values);
  }

  //method
  /**
   * @param value
   * @param values
   * @return a new multi long mediator that belongs to this test and is for the
   *         given values.
   */
  protected final MultiLongMediator expectTheInts(final int value, final int... values) {
    return MultiLongMediator.forValue(value, values);
  }

  //method
  /**
   * @param values
   * @return a new long container mediator that belongs to this test and has the
   *         given values.
   */
  protected final MultiLongMediator expectTheLongs(final Iterable<Long> values) {
    return new MultiLongMediator(values);
  }

  //method
  /**
   * @param value
   * @param values
   * @return a new long container mediator that belongs to this test and has the
   *         given values.
   */
  protected final MultiLongMediator expectTheLongs(final long value, final long... values) {
    return expectTheLongs(value, values);
  }
}
