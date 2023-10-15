//package declaration
package ch.nolix.coreapi.functionapi.genericfunctionapi;

//functional interface
/**
 * A {@link IElementTakerDoubleGetter} has a method that takes an element and
 * returns a double.
 * 
 * @author Silvan Wyss
 * @date 2016-04-01
 * @param <E> is the type of the elements a {@link IElementTakerDoubleGetter}
 *            takes.
 */
@FunctionalInterface
public interface IElementTakerDoubleGetter<E> {

  // method declaration
  /**
   * @param element
   * @return a double for the given element.
   */
  double getOutput(E element);
}
