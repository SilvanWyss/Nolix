//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeapi;

//interface
/**
 * A {@link IOptionalLabelHolder} can have a label.
 * 
 * @author Silvan Wyss
 * @date 2023-02-07
 */
public interface IOptionalLabelHolder {

  //method declaration
  /**
   * @return the label of the current {@link IOptionalLabelHolder}.
   * @throws RuntimeException if the current {@link IOptionalLabelHolder} does not have
   *                          a label.
   */
  String getLabel();

  //method declaration
  /**
   * @return true if the current {@link IOptionalLabelHolder} has a label.
   */
  boolean hasLabel();
}
