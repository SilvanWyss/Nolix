package ch.nolix.coreapi.attributeapi.mandatoryattributeapi;

import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

/**
 * A {@link ILabelHolder} has a label.
 * 
 * @author Silvan Wyss
 * @version 2021-08-26
 */
@AllowDefaultMethodsAsDesignPattern
public interface ILabelHolder {

  /**
   * @return the label of the current {@link ILabelHolder}.
   */
  String getLabel();

  /**
   * @return the label of the current {@link ILabelHolder} in quotes.
   */
  default String getLabelInQuotes() {
    return ("'" + getLabel() + "'");
  }
}
