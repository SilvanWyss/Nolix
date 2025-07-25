package ch.nolix.coreapi.container.sequencesearch;

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
   * @return a new {@link ISequencePattern} from the {@link ISequencePattern} of
   *         the current {@link ISequencePatternNextMediator} with a blank
   *         condition for the next elements of the sequences of the
   *         {@link ISequencePattern}.
   */
  ISequencePattern<E> withBlank();

  /**
   * @param condition
   * @return a new {@link ISequencePattern} from the {@link ISequencePattern} of
   *         the current {@link ISequencePatternNextMediator} with the given
   *         condition for the next elements of the sequences of the
   *         {@link ISequencePattern}.
   * @throws RuntimeException if the given condition is null.
   */
  ISequencePattern<E> withCondition(Predicate<E> condition);
}
