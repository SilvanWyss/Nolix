/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.errorcontrol.validator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonNegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.independent.arraytool.ArrayTool;

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
   * @throws ArgumentIsNullException if the given arguments is null.
   */
  public MultiDoubleMediator(final Iterable<Double> arguments) {
    //Calls constructor of the base class.
    super(arguments);
  }

  /**
   * Creates a new multi double mediator for the given arguments.
   * 
   * @param arguments
   * @throws ArgumentIsNullException if the given arguments is null.
   */
  public MultiDoubleMediator(final double[] arguments) {
    //Calls constructor of the base class.
    super(ARRAY_TOOL.createIterable(arguments));
  }

  /**
   * @param value
   * @throws ArgumentIsNullException  if one of the arguments of this multi double
   *                                  mediator is null.
   * @throws InvalidArgumentException if one of the arguments of this multi double
   *                                  mediator is not bigger than the given value.
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
   * @throws ArgumentIsNullException      if one of the arguments of this multi
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
   * @throws ArgumentIsNullException      if one of the arguments of this multi
   *                                      double mediator is null.
   * @throws NonPositiveArgumentException if one of the arguments of this multi
   *                                      double mediator is not positive.
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
   * @throws ArgumentIsNullException  if one of the arguments of this multi double
   *                                  mediator is null.
   * @throws InvalidArgumentException if one of the argument of this multi double
   *                                  mediator is not smaller than the given
   *                                  value.
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
