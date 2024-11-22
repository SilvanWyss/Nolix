package ch.nolix.coreapi.containerapi.sequencesearchapi;

import java.util.function.Predicate;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;

/**
 * A {@link ISequencePattern} is a pattern for sequences.
 * 
 * -The sequences of a {@link ISequencePattern} must have a defined length.
 * 
 * -The elements of the sequences of a {@link ISequencePattern} must fulfill the
 * element conditions of the {@link ISequencePattern}.
 * 
 * -The sequences of a {@link ISequencePattern} must fulfill the sequence
 * conditions of the {@link ISequencePattern}.
 * 
 * @author Silvan Wyss
 * @version 2023-02-13
 * @param <E> is the type of the elements of the sequences a
 *            {@link ISequencePattern} selects.
 */
public interface ISequencePattern<E> {

  /**
   * Adds a blank condition for the next element of the sequences of the current
   * {@link ISequencePattern}.
   * 
   * @return the current {@link ISequencePattern}.
   */
  ISequencePattern<E> addBlankForNext();

  /**
   * Adds the given condition for the next element of the sequences of the current
   * {@link ISequencePattern}.
   * 
   * @param condition
   * @return the current {@link ISequencePattern}.
   * @throws RuntimeException if the given condition is null.
   */
  ISequencePattern<E> addConditionForNext(Predicate<E> condition);

  /**
   * Adds the given sequenceCondition to the current {@link ISequencePattern}.
   * 
   * @param sequenceCondition
   * @return this {@link ISequencePattern}.
   * @throws RuntimeException if the given sequenceCondition is null.
   * 
   */
  ISequencePattern<E> addSequenceCondition(Predicate<ILinkedList<E>> sequenceCondition);

  /**
   * @param count
   * @return a new {@link ISequencePatternNextMediator} for the current
   *         {@link ISequencePattern} and the given count.
   * @throws RuntimeException if the given count is negative.
   */
  ISequencePatternNextMediator<E> forNext(int count);

  /**
   * @param container
   * @return all sequences from the given container that match the current
   *         {@link ISequencePattern}.
   */
  IContainer<? extends IContainer<E>> getMatchingSequencesFrom(IContainer<E> container);
}
