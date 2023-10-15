//package declaration
package ch.nolix.coreapi.functionapi.genericfunctionapi;

//functional interface
/**
 * A {@link IBooleanTaker} has a method that takes a boolean.
 * 
 * @author Silvan Wyss
 * @date 2018-09-01
 */
@FunctionalInterface
public interface IBooleanTaker {

  // method declaration
  /**
   * Takes the given value.
   * 
   * @param value
   */
  void run(boolean value);
}
