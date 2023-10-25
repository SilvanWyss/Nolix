//package declaration
package ch.nolix.coreapi.attributeapi.mandatoryattributeapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link ILabelHolder} has a label.
 * 
 * @author Silvan Wyss
 * @date 2021-08-26
 */
@AllowDefaultMethodsAsDesignPattern
public interface ILabelHolder {

  //method declaration
  /**
   * @return the label of the current {@link ILabelHolder}.
   */
  String getLabel();

  //method
  /**
   * @return the label of the current {@link ILabelHolder} in quotes.
   */
  default String getLabelInQuotes() {
    return ("'" + getLabel() + "'");
  }
}
