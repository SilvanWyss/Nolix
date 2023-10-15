//package declaration
package ch.nolix.coreapi.functionapi.genericfunctionapi;

//functional interface
/**
 * A {#link IDoubleGetter} has a method that returns a double.
 * 
 * @author Silvan Wyss
 * @date 2016-10-01
 */
@FunctionalInterface
public interface IDoubleGetter {

  // method declaration
  /**
   * @return a double.
   */
  double getOutput();
}
