//package declaration
package ch.nolix.coreapi.functionapi.genericfunctionapi;

//functional interface
/**
 * A {@link ILongGetter} has a method that returns a long.
 * 
 * @author Silvan Wyss
 * @date 2016-10-01
 */
@FunctionalInterface
public interface ILongGetter {

  // method declaration
  /**
   * @return a long.
   */
  long getOutput();
}
