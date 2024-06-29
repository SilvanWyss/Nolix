//package declaration
package ch.nolix.coreapi.stateapi.statemutationapi;

import ch.nolix.coreapi.stateapi.staterequestapi.EmptinessRequestable;

//interface
/**
 * A {@link Clearable} can contain elements that can be removed.
 * 
 * @author Silvan Wyss
 * @version 2016-03-01
 */
public interface Clearable extends EmptinessRequestable {

  //method declaration
  /**
   * Removes the elements of the current {@link Clearable}.
   */
  void clear();
}
