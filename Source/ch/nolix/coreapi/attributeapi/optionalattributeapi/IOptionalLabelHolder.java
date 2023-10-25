//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link IOptionalLabelHolder} can have a label.
 * 
 * @author Silvan Wyss
 * @date 2023-02-07
 */
@AllowDefaultMethodsAsDesignPattern
public interface IOptionalLabelHolder {

  //method declaration
  /**
   * @return the label of the current {@link IOptionalLabelHolder}.
   * @throws RuntimeException if the current {@link IOptionalLabelHolder} does not
   *                          have a label.
   */
  String getLabel();

  //method
  /**
   * @return the label of the current {@link IOptionalLabelHolder} in quotes.
   * @throws RuntimeException if the current {@link IOptionalLabelHolder} does not
   *                          have a label.
   */
  default String getLabelInQuotes() {
    return ("'" + getLabel() + "'");
  }

  //method declaration
  /**
   * @return true if the current {@link IOptionalLabelHolder} has a label.
   */
  boolean hasLabel();
}
