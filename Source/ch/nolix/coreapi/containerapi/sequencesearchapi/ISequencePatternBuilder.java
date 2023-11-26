//package declaration
package ch.nolix.coreapi.containerapi.sequencesearchapi;

import java.util.function.Predicate;

//own imports
import ch.nolix.coreapi.methodapi.requestapi.EmptinessRequestable;
import ch.nolix.coreapi.methodapi.skillapi.IBuilder;

//interface
/**
 * @author Silvan Wyss
 * @date 2023-02-13
 * @param <E> is the type of the elements of the searched-for sequences of a
 *            {@link ISequencePatternBuilder}.
 */
public interface ISequencePatternBuilder<E> extends EmptinessRequestable, IBuilder<ISequencePattern<E>> {

  //method declaration
  /**
   * Adds a blank condition for the next element of the searched-for sequences to
   * the current {@link ISequencePatternBuilder}.
   * 
   * @return the current {@link ISequencePatternBuilder}.
   */
  ISequencePatternBuilder<E> addBlankForNext();

  //method declaration
  /**
   * Adds the given condition for the next element of the searched-for sequences
   * to the current {@link ISequencePatternBuilder}.
   * 
   * @param condition
   * @return the current {@link ISequencePatternBuilder}.
   * @throws RuntimeException if the given condition is null.
   */
  ISequencePatternBuilder<E> addConditionForNext(Predicate<E> condition);

  //method declaration
  /**
   * @param count
   * @return a new {@link INextMediator} for the current
   *         {@link ISequencePatternBuilder} with the given count.
   * @throws RuntimeException if the given count is not positive
   */
  INextMediator<E> forNext(int count);
}
