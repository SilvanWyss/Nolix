//package declaration
package ch.nolix.coreapi.functionapi.genericfunctionapi;

//functional interface
/**
 * A {@link IDoubleTaker} has a method that takes a double.
 * 
 * @author Silvan Wyss
 * @date 2018-09-01
 */
@FunctionalInterface
public interface IDoubleTaker {

  // method declaration
  /**
   * Takes the given value.
   * 
   * @param value
   */
  void run(double value);
}
