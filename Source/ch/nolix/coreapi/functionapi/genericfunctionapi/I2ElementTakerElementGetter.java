//package declaration
package ch.nolix.coreapi.functionapi.genericfunctionapi;

//functional interface
/**
 * A {@link I2ElementTakerElementGetter} has a method that takes 2 elements and
 * returns an element.
 * 
 * @author Silvan Wyss
 * @date 2019-02-17
 * @param <E1> is the type of the first element a
 *             {@link IElementTakerLongGetter} takes.
 * @param <E2> is the type of the second element a
 *             {@link IElementTakerLongGetter} takes.
 * @param <E3> is the type of the element a {@link IElementTakerLongGetter}
 *             returns.
 */
@FunctionalInterface
public interface I2ElementTakerElementGetter<E1, E2, E3> {

  // method declaration
  /**
   * @param element1
   * @param element2
   * @return an element for the given element1 and element2 from the current
   *         {@link I2ElementTakerElementGetter}.
   */
  E3 getOutput(E1 element1, E2 element2);
}
