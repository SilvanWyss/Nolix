//package declaration
package ch.nolix.coreapi.methodapi.mutationapi;

import ch.nolix.coreapi.methodapi.requestapi.EmptinessRequestable;

//interface
/**
 * A {@link Clearable} can contain elements that can be removed.
 * 
 * @author Silvan Wyss
 * @date 2016-03-01
 */
public interface Clearable extends EmptinessRequestable {

  //method declaration
  /**
   * Removes the elements of the current {@link Clearable}.
   */
  void clear();
}
