//package declaration
package ch.nolix.coreapi.functionapi.genericfunctionapi;

//functional interface
/**
 * A {@link IElementTakerElementGetter} has a method that takes an element and
 * returns an element.
 * 
 * @author Silvan Wyss
 * @date 2016-04-01
 * @param <E>  is the type of the elements a {@link IElementTakerElementGetter}
 *             takes.
 * @param <E2> is the type of the elements a {@link IElementTakerElementGetter}
 *             returns.
 */
@FunctionalInterface
public interface IElementTakerElementGetter<E, E2> {

  // method declaration
  /**
   * @param element
   * @return an element for the given element.
   */
  E2 getOutput(E element);
}
