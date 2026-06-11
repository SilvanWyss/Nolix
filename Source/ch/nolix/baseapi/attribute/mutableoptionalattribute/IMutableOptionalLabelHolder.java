/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.IOptionalLabelHolder;

/**
 * A {@link IMutableOptionalLabelHolder} is a {@link IOptionalLabelHolder} whose
 * label can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 */
public interface IMutableOptionalLabelHolder extends IOptionalLabelHolder {
  /**
   * Removes the label of the current {@link IMutableOptionalLabelHolder}.
   */
  void removeLabel();

  /**
   * Sets the label of the current {@link IMutableOptionalLabelHolder}.
   * 
   * @param label
   * @throws RuntimeException if the given label is null or blank.
   */
  void setLabel(String label);
}
