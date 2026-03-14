/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.errorcontrol.validator;

import ch.nolix.base.independent.arraytool.ArrayTool;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NonNegativeArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NonPositiveArgumentException;

/**
 * A multi double mediator is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class MultiDoubleMediator extends MultiArgumentMediator<Double> {
  private static final ArrayTool ARRAY_TOOL = new ArrayTool();

  /**
   * Creates a new multi double mediator for the given arguments.
   * 
   * @param arguments
   * @throws RuntimeException if the given arguments is null.
   */
  public MultiDoubleMediator(final Iterable<Double> arguments) {
    //Calls constructor of the base class.
    super(arguments);
  }

  /**
   * Creates a new multi double mediator for the given arguments.
   * 
   * @param arguments
   * @throws RuntimeException if the given arguments is null.
   */
  public MultiDoubleMediator(final double[] arguments) {
    //Calls constructor of the base class.
    super(ARRAY_TOOL.createIterable(arguments));
  }

  /**
   * @param value
   * @throws RuntimeException if one of the arguments of this multi double
   *                          mediator is null.
   * @throws RuntimeException if one of the arguments of this multi double
   *                          mediator is not bigger than the given value.
   */
  public void areBiggerThan(final double value) {
    //Asserts that the arguments of this multi double mediator are not null.
    areNotNull();

    //Iterates the arguments of this multi double mediator.
    var i = 1;
    for (final double a : getStoredArguments()) {
      //Asserts that the current argument is bigger than the given value.
      if (a <= value) {
        throw //
        InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
          a,
          i + "th argument",
          "is not bigger than " + value);
      }

      //Increments index.
      i++;
    }
  }

  /**
   * @throws RuntimeException             if one of the arguments of this multi
   *                                      double mediator is null.
   * @throws NonNegativeArgumentException if one of the arguments of this multi
   *                                      double mediator is not positive.
   */
  public void areNegative() {
    //Asserts that the arguments of this multi double mediator are not null.
    areNotNull();

    //Iterates the arguments of this multi double mediator.
    var i = 1;
    for (final double a : getStoredArguments()) {
      //Asserts that the current arguemnt is negative.
      if (a > 0) {
        throw NonNegativeArgumentException.forArgumentAndArgumentName(a, i + "th argument");
      }

      //Increments index.
      i++;
    }
  }

  /**
   * @throws RuntimeException if one of the arguments of this multi double
   *                          mediator is null.
   * @throws RuntimeException if one of the arguments of this multi double
   *                          mediator is not positive.
   */
  public void arePositive() {
    //Asserts that the arguments of this multi double mediator are not null.
    areNotNull();

    //Iterates the arguments of this multi double mediator.
    var i = 1;
    for (final double a : getStoredArguments()) {
      //Asserts that the current argument is positive.
      if (a <= 0) {
        throw NonPositiveArgumentException.forArgumentAndArgumentName(a, i + "th argument");
      }

      //Increments index.
      i++;
    }
  }

  /**
   * @param value
   * @throws RuntimeException if one of the arguments of this multi double
   *                          mediator is null.
   * @throws RuntimeException if one of the argument of this multi double mediator
   *                          is not smaller than the given value.
   */
  public void areSmallerThan(final double value) {
    //Asserts that the arguments of this multi double mediator are not null.
    areNotNull();

    //Iterates the arguments of this multi double mediator.
    var i = 1;
    for (final double a : getStoredArguments()) {
      //Asserts that the current argument is smaller than the given value.
      if (a >= value) {
        throw //
        InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
          a,
          i + "the argument",
          "is not smaller than " + value);
      }

      //Increments index.
      i++;
    }
  }
}
