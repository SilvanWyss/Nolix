/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.errorcontrol.exceptionargumentpreparatorimpl;

import ch.nolix.baseapi.commontype.charactertool.CharacterCatalog;
import ch.nolix.baseapi.errorcontrol.exceptionargumentpreparator.IExceptionErrorPredicatePreparator;

/**
 * @author Silvan Wyss
 */
public final class ExceptionErrorPredicatePreparator implements IExceptionErrorPredicatePreparator {
  /**
   * {@inheritDoc}
   */
  @Override
  public String getValidErrorPredicateFromErrorPredicate(final String errorPredicate) {
    if (errorPredicate == null) {
      throw new IllegalArgumentException("The given error predicate is null.");
    }

    if (errorPredicate.isBlank()) {
      throw new IllegalArgumentException("The given error predicate is blank.");
    }

    if (errorPredicate.charAt(errorPredicate.length() - 1) == CharacterCatalog.DOT) {
      throw new IllegalArgumentException("The given error predicate '" + errorPredicate + "' ends with a dot.");
    }

    return errorPredicate;
  }
}
