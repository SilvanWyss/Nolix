package ch.nolix.coreapi.attribute.mutableoptionalattribute;

import ch.nolix.coreapi.attribute.optionalattribute.IOptionalTitleHolder;

/**
 * A {@link IMutableOptionalTitleHolder} is a {@link IOptionalTitleHolder} whose
 * title can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @version 2023-02-09
 */
public interface IMutableOptionalTitleHolder extends IOptionalTitleHolder {

  /**
   * Removes the title of the current {@link IMutableOptionalTitleHolder}.
   */
  void removeTitle();

  /**
   * Sets the title of the current {@link IMutableOptionalTitleHolder}.
   * 
   * @param title
   * @throws RuntimeException if the given title is null.
   * @throws RuntimeException if the given title is blank.
   */
  void setTitle(String title);
}
