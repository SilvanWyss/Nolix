package ch.nolix.core.errorcontrol.exceptionargumentpreparator;

import ch.nolix.coreapi.commontypetool.charactertool.CharacterCatalog;
import ch.nolix.coreapi.errorcontrol.exceptionargumentpreparator.IExceptionErrorPredicatePreparator;

/**
 * @author Silvan Wyss
 * @version 2025-04-06
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
