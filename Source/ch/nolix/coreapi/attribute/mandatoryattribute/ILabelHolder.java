package ch.nolix.coreapi.attribute.mandatoryattribute;

/**
 * A {@link ILabelHolder} has a label.
 * 
 * @author Silvan Wyss
 * @version 2021-08-26
 */
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
