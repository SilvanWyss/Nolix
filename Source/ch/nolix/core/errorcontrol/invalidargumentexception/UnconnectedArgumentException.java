/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.errorcontrol.invalidargumentexception;

import ch.nolix.coreapi.errorcontrol.exceptionargumentbox.ErrorPredicateDto;

/**
 * A {@link UnconnectedArgumentException} is a
 * {@link AbstractInvalidArgumentException} that is supposed to be thrown when a
 * given argument is undesirable not connected.
 * 
 * @author Silvan Wyss
 */
@SuppressWarnings("serial")
public final class UnconnectedArgumentException extends AbstractInvalidArgumentException {
  private static final String ERROR_PREDICATE = "is not connected";

  /**
   * Creates a new {@link UnconnectedArgumentException} for the given argument.
   * 
   * @param argument - Can be null.
   */
  private UnconnectedArgumentException(final Object argument) {
    super(argument, new ErrorPredicateDto(ERROR_PREDICATE));
  }

  /**
   * @param argument - Can be null.
   * @return a new {@link UnconnectedArgumentException} for the given argument.
   */
  public static UnconnectedArgumentException forArgument(final Object argument) {
    return new UnconnectedArgumentException(argument);
  }
}
