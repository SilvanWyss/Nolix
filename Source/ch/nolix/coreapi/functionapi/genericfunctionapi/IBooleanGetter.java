//package declaration
package ch.nolix.coreapi.functionapi.genericfunctionapi;

//functional interface
/**
 * A {@link IBooleanGetter} has a method that returns a boolean.
 * 
 * @author Silvan Wyss
 * @date 2016-07-01
 */
@FunctionalInterface
public interface IBooleanGetter {

  // method declaration
  /**
   * @return a boolean.
   */
  boolean getOutput();
}
