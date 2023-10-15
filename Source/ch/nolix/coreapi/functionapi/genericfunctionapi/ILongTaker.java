//package declaration
package ch.nolix.coreapi.functionapi.genericfunctionapi;

//functional interface
/**
 * A {@link ILongTaker} has a method that takes a long.
 * 
 * @author Silvan Wyss
 * @date 2016-10-01
 */
@FunctionalInterface
public interface ILongTaker {

  // method declaration
  /**
   * Takes the given value.
   * 
   * @param value
   */
  void run(long value);
}
