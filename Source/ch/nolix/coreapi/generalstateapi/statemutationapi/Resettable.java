//package declaration
package ch.nolix.coreapi.generalstateapi.statemutationapi;

//interface
/**
 * A {@link Resettable} can be reset.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 */
public interface Resettable {

  //method declaration
  /**
   * Resets the current {@link Resettable}.
   */
  void reset();
}
