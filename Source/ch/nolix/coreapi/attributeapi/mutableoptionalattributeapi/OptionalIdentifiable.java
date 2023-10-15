//package declaration
package ch.nolix.coreapi.attributeapi.mutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.optionalattributeapi.OptionalIdentified;

//interface
/**
 * A {@link OptionalIdentifiable} is a {@link OptionalIdentified} whose id can
 * be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-02-09
 */
public interface OptionalIdentifiable extends OptionalIdentified {

  // method declaration
  /**
   * Removes the id of the current {@link OptionalIdentifiable}.
   */
  void removeId();

  // method declaration
  /**
   * Sets the id of the current {@link OptionalIdentifiable}.
   * 
   * @param id
   * @throws RuntimeException if the given id is null.
   * @throws RuntimeException if the given id is blank.
   */
  void setId(String id);
}
