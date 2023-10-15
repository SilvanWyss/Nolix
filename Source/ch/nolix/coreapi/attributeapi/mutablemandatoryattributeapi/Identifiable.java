//package declaration
package ch.nolix.coreapi.attributeapi.mutablemandatoryattributeapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Identified;

//interface
/**
 * A {@link Identifiable} is a {@link Identified} whose id can be set
 * programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-02-09
 */
public interface Identifiable extends Identified {

  // method declaration
  /**
   * Sets the id of the current {@link Identifiable}.
   * 
   * @param id
   * @throws RuntimeException if the given id is null.
   * @throws RuntimeException if the given id is blank.
   */
  void setId(String id);
}
