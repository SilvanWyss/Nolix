/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.LabelHolder;

/**
 * A {@link MutableLabelHolder} is a {@link LabelHolder} whose label can be
 * set programmatically.
 * 
 * @author Silvan Wyss
 */
public interface MutableLabelHolder extends LabelHolder {
  /**
   * Sets the label of the current {@link MutableLabelHolder}.
   * 
   * @param label
   * @throws RuntimeException if the given label is null or blank
   */
  void setLabel(String label);
}
