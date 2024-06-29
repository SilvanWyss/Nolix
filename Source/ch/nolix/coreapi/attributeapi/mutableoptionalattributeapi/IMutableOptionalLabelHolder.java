//package declaration
package ch.nolix.coreapi.attributeapi.mutableoptionalattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalLabelHolder;

//interface
/**
 * A {@link IMutableOptionalLabelHolder} is a {@link IOptionalLabelHolder} whose
 * label can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @version 2023-10-25
 */
public interface IMutableOptionalLabelHolder extends IOptionalLabelHolder {

  //method declaration
  /**
   * Removes the label of the current {@link IMutableOptionalLabelHolder}.
   */
  void removeLabel();

  //method declaration
  /**
   * Sets the label of the current {@link IMutableOptionalLabelHolder}.
   * 
   * @param label
   * @throws RuntimeException if the given label is null.
   * @throws RuntimeException if the given label is blank.
   */
  void setLabel(String label);
}
