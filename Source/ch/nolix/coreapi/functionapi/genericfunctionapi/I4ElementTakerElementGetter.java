//package declaration
package ch.nolix.coreapi.functionapi.genericfunctionapi;

//functional interface
/**
 * A {@link I4ElementTakerElementGetter} has a method that takes 4 elements and
 * returns an element.
 * 
 * @author Silvan Wyss
 * @date 2019-03-03
 * @param <E1> is the type of the first element a
 *             {@link IElementTakerLongGetter} takes.
 * @param <E2> is the type of the second element a
 *             {@link IElementTakerLongGetter} takes.
 * @param <E3> is the type of the third element a
 *             {@link IElementTakerLongGetter} takes.
 * @param <E4> is the type of the fourth element a
 *             {@link IElementTakerLongGetter} takes.
 * @param <E5> is the type of the element a {@link IElementTakerLongGetter}
 *             returns.
 */
@FunctionalInterface
public interface I4ElementTakerElementGetter<E1, E2, E3, E4, E5> {

  // method declaration
  /**
   * @param element1
   * @param element2
   * @param element3
   * @param element4
   * @return an element for the given element1, element2, element3 and element4
   *         from the current {@link I4ElementTakerElementGetter}.
   */
  E5 getOutput(E1 element1, E2 element2, E3 element3, E4 element4);
}
