//package declaration
package ch.nolix.coreapi.attributeapi.mutableoptionalattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalTitleHolder;

//interface
/**
 * A {@link IMutableOptionalTitleHolder} is a {@link IOptionalTitleHolder} whose
 * title can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @version 2023-02-09
 */
public interface IMutableOptionalTitleHolder extends IOptionalTitleHolder {

  //method declaration
  /**
   * Removes the title of the current {@link IMutableOptionalTitleHolder}.
   */
  void removeTitle();

  //method declaration
  /**
   * Sets the title of the current {@link IMutableOptionalTitleHolder}.
   * 
   * @param title
   * @throws RuntimeException if the given title is null.
   * @throws RuntimeException if the given title is blank.
   */
  void setTitle(String title);
}
