//package declaration
package ch.nolix.coreapi.containerapi.listapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.generalstateapi.statemutationapi.Clearable;
import ch.nolix.coreapi.programstructureapi.builderapi.Copyable;

//interface
/**
 * A {@link IArrayList} is a {@link IContainer} that can add and remove
 * elements.
 * 
 * @author Silvan Wyss
 * @date 2024-01-30
 * @param <E> is the type of the elements of a {@link IArrayList}.
 */
public interface IArrayList<E> extends Clearable, Copyable<IArrayList<E>>, IContainer<E> {

  //method declaration
  /**
   * Adds the given given elements at the end of the current {@link IArrayList}.
   * The elements will be added in the given order.
   * 
   * @param element
   * @param elements
   * @throws RuntimeException if the given element is null.
   * @throws RuntimeException if the given elements is null.
   * @throws RuntimeException if one of the given elements is null.
   */
  void addAtEnd(E element, @SuppressWarnings("unchecked") E... elements);

  //method declaration
  /**
   * Adds the given elements at the end of the current {@link IArrayList}. The
   * elements will be added in the given order.
   * 
   * @param elements
   * @throws RuntimeException if the given elements is null.
   * @throws RuntimeException if one of the given elements is null.
   */
  void addAtEnd(E[] elements);
}
