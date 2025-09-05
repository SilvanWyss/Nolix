package ch.nolix.coreapi.attribute.mandatoryattribute;

/**
 * A {@link ITitleHolder} has a title.
 * 
 * @author Silvan Wyss
 * @version 2019-07-26
 */
public interface ITitleHolder {
  /**
   * @return the title of the current {@link ITitleHolder}.
   */
  String getTitle();

  /**
   * @return the title of the current {@link ITitleHolder} in quotes.
   */
  default String getTitleInQuotes() {
    return ("'" + getTitle() + "'");
  }
}
