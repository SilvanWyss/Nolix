//package declaration
package ch.nolix.coreapi.functionapi.genericfunctionapi;

//functional interface
/**
 * A {@link IElementTakerIntGetter} has a method that takes an element and
 * returns an int.
 * 
 * @author Silvan Wyss
 * @date 2016-04-01
 * @param <E> is the type of the elements a {@link IElementTakerIntGetter}
 *            takes.
 */
@FunctionalInterface
public interface IElementTakerIntGetter<E> {

  // method declaration
  /**
   * @param element
   * @return an int for the given element.
   */
  int getOutput(E element);
}
