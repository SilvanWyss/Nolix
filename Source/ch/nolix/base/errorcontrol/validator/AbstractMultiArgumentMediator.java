/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.errorcontrol.validator;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.misc.variable.PluralLowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @param <A> is the type of the arguments of a
 *            {@link AbstractMultiArgumentMediator}.
 */
public abstract class AbstractMultiArgumentMediator<A> { //NOSONAR: The AbstractMultiArgumentMediator is a super type.
  private final Iterable<A> arguments;

  /**
   * Creates a new {@link AbstractMultiArgumentMediator} for the given arguments.
   * 
   * @param arguments
   * @throws RuntimeException if the given arguments is null..
   */
  protected AbstractMultiArgumentMediator(final Iterable<A> arguments) {
    if (arguments == null) {
      throw ArgumentIsNullException.forArgumentName(PluralLowerCaseVariableCatalog.ARGUMENTS);
    }

    this.arguments = arguments;
  }

  /**
   * @throws RuntimeException if one of the arguments of the current
   *                          {@link AbstractMultiArgumentMediator} is null.
   */
  public final void areNotNull() {
    var index = 1;

    for (final var a : getStoredArguments()) {
      if (a == null) {
        throw ArgumentIsNullException.forArgumentName(index + "th argument");
      }

      index++;
    }
  }

  /**
   * @return the arguments of the current {@link AbstractMultiArgumentMediator}.
   */
  protected final Iterable<A> getStoredArguments() {
    return arguments;
  }
}
