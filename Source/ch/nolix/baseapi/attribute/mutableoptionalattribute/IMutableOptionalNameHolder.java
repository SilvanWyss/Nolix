/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.IOptionalNameHolder;

/**
 * A {@link IMutableOptionalNameHolder} is a {@link IOptionalNameHolder} whose
 * name can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 */
public interface IMutableOptionalNameHolder extends IOptionalNameHolder {
  /**
   * Removes the name of the current {@link IMutableOptionalNameHolder}.
   */
  void removeName();

  /**
   * Sets the name of the current {@link IMutableOptionalNameHolder}.
   * 
   * @param name
   * @throws RuntimeException if the given name is null or blank.
   */
  void setName(String name);
}
