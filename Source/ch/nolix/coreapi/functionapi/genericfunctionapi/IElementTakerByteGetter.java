//package declaration
package ch.nolix.coreapi.functionapi.genericfunctionapi;

//functional interface
/**
 * A {@link IElementTakerByteGetter} has a method that takes an element and
 * returns a byte.
 * 
 * @author Silvan Wyss
 * @date 2019-03-05
 * @param <E> is the type of the elements a {@link IElementTakerByteGetter}
 *            takes.
 */
@FunctionalInterface
public interface IElementTakerByteGetter<E> {

  // method declaration
  /**
   * @param element
   * @return a byte for the given element.
   */
  byte getOutput(E element);
}
