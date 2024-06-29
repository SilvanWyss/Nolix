//package declaration
package ch.nolix.coreapi.stateapi.statemutationapi;

//interface
/**
 * A {@link Resettable} can be reset.
 * 
 * @author Silvan Wyss
 * @version 2016-01-01
 */
public interface Resettable {

  //method declaration
  /**
   * Resets the current {@link Resettable}.
   */
  void reset();
}
