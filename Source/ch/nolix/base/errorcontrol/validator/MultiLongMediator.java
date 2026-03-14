/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.errorcontrol.validator;

import ch.nolix.base.independent.arraytool.ArrayTool;
import ch.nolix.base.independent.list.List;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NonPositiveArgumentException;

/**
 * A long container mediator is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class MultiLongMediator extends MultiArgumentMediator<Long> {
  private static final ArrayTool ARRAY_TOOL = new ArrayTool();

  /**
   * Creates a new long container mediator with the given arguments.
   * 
   * @param arguments
   * @throws RuntimeException if the given argument container is null.
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
    final List<Long> allValues = List.createEmpty();
    allValues.addAtEnd((long) value);
    for (final var v : values) {
      allValues.addAtEnd((long) v);
    }

    return new MultiLongMediator(allValues);
  }

  /**
   * @throws RuntimeException      if one of the arguments of this long
   *                                      container mediator is null.
   * @throws RuntimeException if one of the arguments of this long
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
        throw NonPositiveArgumentException.forArgumentAndArgumentName(a, index + "th");
      }

      //Increments the index.
      index++;
    }
  }
}
