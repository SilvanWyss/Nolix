package ch.nolix.coreapi.containerapi.sequencesearchapi;

import java.util.function.Predicate;

/**
 * @author Silvan Wyss
 * @version 2024-11-22
 * @param <E> is the type of the elements of the sequences of the
 *            {@link ISequencePattern} of a
 *            {@link ISequencePatternNextMediator}.
 */
public interface ISequencePatternNextMediator<E> {

  /**
   * Adds a blank condition for the next elements of the sequences of the
   * {@link ISequencePattern} of the current {@link ISequencePatternNextMediator}.
   * 
   * @return the {@link ISequencePattern} of the current
   *         {@link ISequencePatternNextMediator}.
   */
  ISequencePattern<E> addBlank();

  /**
   * Adds the given condition for the next elements of the sequences of the
   * {@link ISequencePattern} of the current {@link ISequencePatternNextMediator}.
   * 
   * @param condition
   * @return the {@link ISequencePattern} of the current
   *         {@link ISequencePatternNextMediator}.
   * @throws RuntimeException if the given condition is null.
   */
  ISequencePattern<E> addCondition(Predicate<E> condition);
}
