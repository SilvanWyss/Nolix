/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.errorcontrol.validator;

import ch.nolix.base.independent.arraytool.ArrayTool;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;

/**
 * A multi argument mediator is an mediator for several arguments of the same
 * type. A multi argument mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @param <A> is the type of the arguments of a multi argument mediator.
 */
public class MultiArgumentMediator<A> {
  private static final ArrayTool ARRAY_TOOL = new ArrayTool();

  private final Iterable<A> arguments;

  /**
   * Creates a new multi argument mediator with the given arguments.
   * 
   * @param arguments
   * @throws RuntimeException if the given arguments is null..
   */
  MultiArgumentMediator(final Iterable<A> arguments) {
    //Asserts that the given arguments is not null.
    if (arguments == null) {
      throw ArgumentIsNullException.forArgumentName("arguments");
    }

    //Sets the arguments of this multi argument mediator.
    this.arguments = arguments;
  }

  /**
   * Creates a new multi argument mediator with the given arguments.
   * 
   * @param arguments
   * @throws RuntimeException if the given arguments is null.
   */
  MultiArgumentMediator(final A[] arguments) {
    //Calls other constructor.
    this(ARRAY_TOOL.createIterable(arguments));
  }

  /**
   * @throws RuntimeException if one of the arguments of this multi argument
   *                          mediator is null.
   */
  public final void areNotNull() {
    //Iterates the arguments of this multi argument mediator.
    var index = 1;
    for (final A a : getStoredArguments()) {
      //Asserts that the current argument is not null.
      if (a == null) {
        throw ArgumentIsNullException.forArgumentName(index + "th argument");
      }

      //Increments the index.
      index++;
    }
  }

  /**
   * @return the arguments of this multi argument mediator.
   */
  protected final Iterable<A> getStoredArguments() {
    return arguments;
  }
}
