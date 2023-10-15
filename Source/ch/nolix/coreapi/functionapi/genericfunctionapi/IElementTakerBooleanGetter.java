//package declaration
package ch.nolix.coreapi.functionapi.genericfunctionapi;

//functional interface
/**
 * A {@link IElementTakerBooleanGetter} has a method that takes an element and
 * returns a boolean.
 * 
 * @author Silvan Wyss
 * @date 2016-04-01
 * @param <E> is the type of the elements a {@link IElementTakerBooleanGetter}
 *            takes.
 */
@FunctionalInterface
public interface IElementTakerBooleanGetter<E> {

  // method declaration
  /**
   * @param element
   * @return a boolean for the given element.
   */
  boolean getOutput(E element);
}
