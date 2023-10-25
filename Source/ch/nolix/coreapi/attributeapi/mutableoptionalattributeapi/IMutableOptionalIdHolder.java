//package declaration
package ch.nolix.coreapi.attributeapi.mutableoptionalattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalIdHolder;

//interface
/**
 * A {@link IMutableOptionalIdHolder} is a {@link IOptionalIdHolder} whose id
 * can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-02-09
 */
public interface IMutableOptionalIdHolder extends IOptionalIdHolder {

  //method declaration
  /**
   * Removes the id of the current {@link IMutableOptionalIdHolder}.
   */
  void removeId();

  //method declaration
  /**
   * Sets the id of the current {@link IMutableOptionalIdHolder}.
   * 
   * @param id
   * @throws RuntimeException if the given id is null.
   * @throws RuntimeException if the given id is blank.
   */
  void setId(String id);
}
