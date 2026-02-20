/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.ILabelHolder;

/**
 * A {@link IMutableLabelHolder} is a {@link ILabelHolder} whose label can be
 * set programmatically.
 * 
 * @author Silvan Wyss
 */
public interface IMutableLabelHolder extends ILabelHolder {
  /**
   * Sets the label of the current {@link IMutableLabelHolder}.
   * 
   * @param label
   * @throws RuntimeException if the given label is null.
   * @throws RuntimeException if the given label is blank.
   */
  void setLabel(String label);
}
