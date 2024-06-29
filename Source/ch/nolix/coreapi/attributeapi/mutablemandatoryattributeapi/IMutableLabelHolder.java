//package declaration
package ch.nolix.coreapi.attributeapi.mutablemandatoryattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ILabelHolder;

//interface
/**
 * A {@link IMutableLabelHolder} is a {@link ILabelHolder} whose label can be
 * set programmatically.
 * 
 * @author Silvan Wyss
 * @version 2023-10-25
 */
public interface IMutableLabelHolder extends ILabelHolder {

  //method declaration
  /**
   * Sets the label of the current {@link IMutableLabelHolder}.
   * 
   * @param label
   * @throws RuntimeException if the given label is null.
   * @throws RuntimeException if the given label is blank.
   */
  void setLabel(String label);
}
