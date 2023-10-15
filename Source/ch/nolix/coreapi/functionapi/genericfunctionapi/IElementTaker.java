//package declaration
package ch.nolix.coreapi.functionapi.genericfunctionapi;

//functional interface
/**
 * A {@link IElementTaker} has a method that takes an element.
 * 
 * @author Silvan Wyss
 * @date 2016-10-01
 * @param <E> is the type of the elements a {@link IElementTaker} takes.
 */
@FunctionalInterface
public interface IElementTaker<E> {

  // method declaration
  /**
   * Takes the given element.
   * 
   * @param element
   */
  void run(E element);
}
