//package declaration
package ch.nolix.coreapi.attributeapi.mutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalTitleHolder;

//interface
/**
 * A {@link OptionalTitleable} is a {@link IOptionalTitleHolder} whose title can be
 * set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-02-09
 */
public interface OptionalTitleable extends IOptionalTitleHolder {

  //method declaration
  /**
   * Removes the title of the current {@link OptionalTitleable}.
   */
  void removeTitle();

  //method declaration
  /**
   * Sets the title of the current {@link OptionalTitleable}.
   * 
   * @param title
   * @throws RuntimeException if the given title is null.
   * @throws RuntimeException if the given title is blank.
   */
  void setTitle(String title);
}
