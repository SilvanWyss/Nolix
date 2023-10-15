//package declaration
package ch.nolix.coreapi.functionapi.genericfunctionapi;

//functional interface
/**
 * A {@link ILongTakerLongGetter} has a method that takes a long and returns a
 * long.
 * 
 * @author Silvan Wyss
 * @date 2017-12-08
 */
@FunctionalInterface
public interface ILongTakerLongGetter {

  // method declaration
  /**
   * @param value
   * @return a long for the given value.
   */
  long getOutput(long value);
}
