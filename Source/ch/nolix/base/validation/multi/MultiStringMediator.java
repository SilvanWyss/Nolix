/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.multi;

import ch.nolix.base.foundation.arrayiterableview.ArrayIterableView;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;

/**
 * A {@link MultiStringMediator} is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class MultiStringMediator extends AbstractMultiArgumentMediator<String> {
  /**
   * Creates a new {@link MultiStringMediator} for the given arguments.
   * 
   * @param arguments
   * @throws RuntimeException if the given arguments is null
   */
  private MultiStringMediator(final Iterable<String> arguments) {
    super(arguments);
  }

  /**
   * @param arguments
   * @return a new {@link MultiStringMediator} for the given arguments
   * @throws RuntimeException if the given arguments is null
   */
  public static MultiStringMediator forArguments(final Iterable<String> arguments) {
    return new MultiStringMediator(arguments);
  }

  /**
   * @param arguments
   * @return a new {@link MultiStringMediator} for the given arguments
   * @throws RuntimeException if the given arguments is null
   */
  public static MultiStringMediator forArguments(final String[] arguments) {
    final var argumentsIterableView = ArrayIterableView.forArray(arguments);

    return new MultiStringMediator(argumentsIterableView);
  }

  /**
   * @throws RuntimeException if one of the arguments of the current
   *                          {@link MultiStringMediator} is null or blank
   */
  public void areNotBlank() {
    var index = 1;

    for (final var a : getStoredArguments()) {
      if (a == null) {
        throw ArgumentIsNullException.forArgumentName(index + "th argument");
      }

      if (a.isBlank()) {
        throw //
        InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(a, index + "th argument", "is blank");
      }

      index++;
    }
  }

  /**
   * @throws RuntimeException if one of the arguments of the current
   *                          {@link MultiStringMediator} is null or empty
   */
  public void areNotEmpty() {
    var index = 1;

    for (final var a : getStoredArguments()) {
      if (a == null) {
        throw ArgumentIsNullException.forArgumentName(index + "th argument");
      }

      if (a.isEmpty()) {
        throw EmptyArgumentException.forArgumentAndArgumentName(a, "th argument");
      }

      index++;
    }
  }
}
