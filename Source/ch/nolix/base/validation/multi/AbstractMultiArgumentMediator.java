/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.multi;

import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PluralLowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <A> the type of the arguments of a
 *            {@link AbstractMultiArgumentMediator}.
 */
public abstract class AbstractMultiArgumentMediator<A> { // NOSONAR: The AbstractMultiArgumentMediator is a super type.
  private final Iterable<A> arguments;

  /**
   * Creates a new {@link AbstractMultiArgumentMediator} for the given arguments.
   * 
   * @param arguments
   * @throws RuntimeException if the given arguments is null
   */
  protected AbstractMultiArgumentMediator(final Iterable<A> arguments) {
    if (arguments == null) {
      throw ArgumentIsNullException.forArgumentName(PluralLowerCaseVariableNameCatalog.ARGUMENTS);
    }

    this.arguments = arguments;
  }

  /**
   * @throws RuntimeException if one of the arguments of the current
   *                          {@link AbstractMultiArgumentMediator} is null
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
   * @return the arguments of the current {@link AbstractMultiArgumentMediator}
   */
  protected final Iterable<A> getStoredArguments() {
    return arguments;
  }
}
