/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.sequencesearch;

import java.util.function.Predicate;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * A {@link ISequencePattern} is a pattern for sequences.
 * 
 * -The sequences of a {@link ISequencePattern} must have a defined length.
 * 
 * -The elements of the sequences of a {@link ISequencePattern} must fulfill the
 * element conditions of the {@link ISequencePattern}.
 * 
 * -The sequences of a {@link ISequencePattern} must fulfill all sequence
 * conditions of the {@link ISequencePattern}.
 * 
 * @author Silvan Wyss
 * @param <E> the type of the elements of the sequences a
 *            {@link ISequencePattern} selects.
 */
public interface ISequencePattern<E> {
  /**
   * @return a new {@link ISequencePattern} from the current
   *         {@link ISequencePattern } with a blank condition for the next element
   *         of the sequences of the {@link ISequencePattern}.
   */
  ISequencePattern<E> withBlankForNext();

  /**
   * @param condition
   * @return a new {@link ISequencePattern} from the current
   *         {@link ISequencePattern } with the given condition for the next
   *         element of the sequences of the {@link ISequencePattern}
   * @throws RuntimeException if the given condition is null
   */
  ISequencePattern<E> withConditionForNext(Predicate<E> condition);

  /**
   * @param conditions
   * @return a new {@link ISequencePattern} from the current
   *         {@link ISequencePattern } with the given conditions for the next
   *         elements of the sequences of the {@link ISequencePattern}
   * @throws RuntimeException if the given condition is null
   * @throws RuntimeException if one of the given conditions is null.
   */
  ISequencePattern<E> withConditionsForNexts(ExtendedIterable<Predicate<E>> conditions);

  /**
   * Adds the given sequenceCondition to the current {@link ISequencePattern}.
   * 
   * @param sequenceCondition
   * @return a new {@link ISequencePattern} from the current
   *         {@link ISequencePattern } with the given sequenceCondition for the
   *         sequences the {@link ISequencePattern}
   * @throws RuntimeException if the given sequenceCondition is null
   * 
   */
  ISequencePattern<E> withSequenceCondition(Predicate<ExtendedIterable<E>> sequenceCondition);

  /**
   * @param count
   * @return a new {@link ISequencePatternNextMediator} for the current
   *         {@link ISequencePattern} and the given count
   * @throws RuntimeException if the given count is negative
   */
  ISequencePatternNextMediator<E> forNext(int count);

  /**
   * @param container
   * @return all sequences from the given container that match the current
   *         {@link ISequencePattern}
   */
  ExtendedIterable<? extends ExtendedIterable<E>> getMatchingSequencesFrom(ExtendedIterable<E> container);
}
