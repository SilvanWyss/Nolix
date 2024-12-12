package ch.nolix.core.errorcontrol.validator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.independent.arraytool.ArrayTool;
import ch.nolix.core.independent.list.List;

/**
 * A long container mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2017-01-01
 */
public final class MultiLongMediator extends MultiArgumentMediator<Long> {

  private static final ArrayTool ARRAY_TOOL = new ArrayTool();

  /**
   * Creates a new long container mediator with the given arguments.
   * 
   * @param arguments
   * @throws ArgumentIsNullException if the given argument container is null.
   */
  public MultiLongMediator(final Iterable<Long> arguments) {

    //Calls constructor of the base class.
    super(arguments);
  }

  public MultiLongMediator(final long[] arguments) {

    //Calls constructor if the base class.
    super(ARRAY_TOOL.createIterable(arguments));
  }

  public static MultiLongMediator forValue(final int value, final int[] values) {

    final var allValues = new List<Long>();
    allValues.addAtEnd((long) value);
    for (final var v : values) {
      allValues.addAtEnd((long) v);
    }

    return new MultiLongMediator(allValues);
  }

  /**
   * @throws ArgumentIsNullException      if one of the arguments of this long
   *                                      container mediator is null.
   * @throws NonPositiveArgumentException if one of the arguments of this long
   *                                      container mediator is not positive.
   */
  public void arePositive() {

    //Asserts that the arguments of this long container mediator are not null.
    areNotNull();

    //Iterates through the arguments of this long container mediator.
    var index = 1;
    for (long a : getStoredArguments()) {

      //Asserts that the current argument is positive.
      if (a <= 0) {
        throw NonPositiveArgumentException.forArgumentNameAndArgument(index + "th", a);
      }

      //Increments the index.
      index++;
    }
  }
}
