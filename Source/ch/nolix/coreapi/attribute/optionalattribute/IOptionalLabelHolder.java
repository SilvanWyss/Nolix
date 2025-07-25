package ch.nolix.coreapi.attribute.optionalattribute;

import ch.nolix.coreapi.structure.typemarker.AllowDefaultMethodsAsDesignPattern;

/**
 * A {@link IOptionalLabelHolder} can have a label.
 * 
 * @author Silvan Wyss
 * @version 2023-02-07
 */
@AllowDefaultMethodsAsDesignPattern
public interface IOptionalLabelHolder {

  /**
   * @return the label of the current {@link IOptionalLabelHolder}.
   * @throws RuntimeException if the current {@link IOptionalLabelHolder} does not
   *                          have a label.
   */
  String getLabel();

  /**
   * @return the label of the current {@link IOptionalLabelHolder} in quotes.
   * @throws RuntimeException if the current {@link IOptionalLabelHolder} does not
   *                          have a label.
   */
  default String getLabelInQuotes() {
    return ("'" + getLabel() + "'");
  }

  /**
   * @return true if the current {@link IOptionalLabelHolder} has a label.
   */
  boolean hasLabel();
}
