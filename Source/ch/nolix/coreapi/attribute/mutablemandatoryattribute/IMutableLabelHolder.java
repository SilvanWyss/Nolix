package ch.nolix.coreapi.attribute.mutablemandatoryattribute;

import ch.nolix.coreapi.attribute.mandatoryattribute.ILabelHolder;

/**
 * A {@link IMutableLabelHolder} is a {@link ILabelHolder} whose label can be
 * set programmatically.
 * 
 * @author Silvan Wyss
 * @version 2023-10-25
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
