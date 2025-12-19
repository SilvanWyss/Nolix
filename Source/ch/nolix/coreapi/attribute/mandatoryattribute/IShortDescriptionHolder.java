package ch.nolix.coreapi.attribute.mandatoryattribute;

/**
 * A {@link IShortDescriptionHolder} has a short description.
 * 
 * @author Silvan Wyss
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
