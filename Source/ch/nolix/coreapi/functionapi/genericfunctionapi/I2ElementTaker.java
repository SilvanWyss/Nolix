//package declaration
package ch.nolix.coreapi.functionapi.genericfunctionapi;

//functional interface
/**
 * A {@link I2ElementTaker} has a method that takes 2 elements.
 * 
 * @author Silvan Wyss
 * @date 2020-07-27
 * @param <E1> is the type of the first element a {@link I2ElementTaker} takes.
 * @param <E2> is the type of the second element a {@link I2ElementTaker} takes.
 */
@FunctionalInterface
public interface I2ElementTaker<E1, E2> {

  // method declaration
  /**
   * Lets the current {@link I2ElementTaker} take the given element1 and element2.
   * 
   * @param element1
   * @param element2
   */
  void run(E1 element1, E2 element2);
}
