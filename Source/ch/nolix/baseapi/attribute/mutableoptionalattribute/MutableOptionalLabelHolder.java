/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.OptionalLabelHolder;

/**
 * A {@link MutableOptionalLabelHolder} is a {@link OptionalLabelHolder} whose
 * label can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 */
public interface MutableOptionalLabelHolder extends OptionalLabelHolder {
  /**
   * Removes the label of the current {@link MutableOptionalLabelHolder}.
   */
  void removeLabel();

  /**
   * Sets the label of the current {@link MutableOptionalLabelHolder}.
   * 
   * @param label
   * @throws RuntimeException if the given label is null or blank
   */
  void setLabel(String label);
}
