package ch.nolix.coreapi.attribute.mandatoryattribute;

/**
 * A {@link IShortDescriptionHolder} has a short description.
 * 
 * @author Silvan Wyss
 * @version 2025-08-15
 */
public interface IShortDescriptionHolder {
  /**
   * @return the short description of the current {@link IShortDescriptionHolder}.
   */
  String getShortDescription();

  /**
   * @return the short description of the current {@link IShortDescriptionHolder}
   *         in quotes.
   */
  default String getShortDescriptionInQuotes() {
    return ("'" + getShortDescription() + "'");
  }
}
