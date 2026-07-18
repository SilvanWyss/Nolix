/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.OptionalIdHolder;

/**
 * A {@link MutableOptionalIdHolder} is a {@link OptionalIdHolder} whose id can
 * be set and removed programmatically.
 * 
 * @author Silvan Wyss
 */
public interface MutableOptionalIdHolder extends OptionalIdHolder {
  /**
   * Removes the id of the current {@link MutableOptionalIdHolder}.
   */
  void removeId();

  /**
   * Sets the id of the current {@link MutableOptionalIdHolder}.
   * 
   * @param id
   * @throws RuntimeException if the given id is null or blank.
   */
  void setId(String id);
}
