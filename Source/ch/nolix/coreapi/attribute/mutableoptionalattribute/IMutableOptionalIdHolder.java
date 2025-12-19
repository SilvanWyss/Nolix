package ch.nolix.coreapi.attribute.mutableoptionalattribute;

import ch.nolix.coreapi.attribute.optionalattribute.IOptionalIdHolder;

/**
 * A {@link IMutableOptionalIdHolder} is a {@link IOptionalIdHolder} whose id
 * can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 */
public interface IMutableOptionalIdHolder extends IOptionalIdHolder {
  /**
   * Removes the id of the current {@link IMutableOptionalIdHolder}.
   */
  void removeId();

  /**
   * Sets the id of the current {@link IMutableOptionalIdHolder}.
   * 
   * @param id
   * @throws RuntimeException if the given id is null.
   * @throws RuntimeException if the given id is blank.
   */
  void setId(String id);
}
