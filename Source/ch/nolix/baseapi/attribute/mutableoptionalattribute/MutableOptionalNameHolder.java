/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.OptionalNameHolder;

/**
 * A {@link MutableOptionalNameHolder} is a {@link OptionalNameHolder} whose
 * name can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 */
public interface MutableOptionalNameHolder extends OptionalNameHolder {
  /**
   * Removes the name of the current {@link MutableOptionalNameHolder}.
   */
  void removeName();

  /**
   * Sets the name of the current {@link MutableOptionalNameHolder}.
   * 
   * @param name
   * @throws RuntimeException if the given name is null or blank.
   */
  void setName(String name);
}
