//package declaration
package ch.nolix.coreapi.functionapi.genericfunctionapi;

//functional interface
/**
 * A {@link IElementGetter} has a method that returns an element.
 * 
 * @author Silvan Wyss
 * @date 2016-10-01
 * @param <E> is the type of the elements a {@link IElementGetter} returns.
 */
@FunctionalInterface
public interface IElementGetter<E> {

  // method declaration
  /**
   * @return an element.
   */
  E getOutput();
}
