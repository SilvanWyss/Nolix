//package declaration
package ch.nolix.coreapi.containerapi.sequencesearchapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
/**
 * @author Silvan Wyss
 * @version 2023-02-13
 * @param <E> is the type of the elements of the sequences a
 *            {@link ISequencePattern} selects.
 */
public interface ISequencePattern<E> {

  //method declaration
  /**
   * @param container
   * @return all sequences from the given container that match the current
   *         {@link ISequencePattern}.
   */
  IContainer<? extends IContainer<E>> getMatchingSequencesFrom(IContainer<E> container);
}
