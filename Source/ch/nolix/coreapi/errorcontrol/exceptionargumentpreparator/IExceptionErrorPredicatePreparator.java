package ch.nolix.coreapi.errorcontrol.exceptionargumentpreparator;

/**
 * A {@link IExceptionErrorPredicatePreparator} provides method to prepare error
 * predicates for {@link Exception}s.
 * 
 * @author Silvan Wyss
 * @version 2025-04-06
 */
public interface IExceptionErrorPredicatePreparator {

  /**
   * @param errorPredicate
   * @return a validated error predicate from the given errorPredicate.
   * @throws IllegalArgumentException if the given errorPredicate is null, blank
   *                                  or ends with a dot.
   */
  String getValidErrorPredicateFromErrorPredicate(String errorPredicate);
}
