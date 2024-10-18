package ch.nolix.coreapi.attributeapi.optionalattributeapi;

import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

/**
 * A {@link IOptionalTitleHolder} can have a title.
 * 
 * @author Silvan Wyss
 * @version 2023-02-06
 */
@AllowDefaultMethodsAsDesignPattern
public interface IOptionalTitleHolder {

  /**
   * @return the title of the current {@link IOptionalTitleHolder}.
   * @throws RuntimeException if the current {@link IOptionalTitleHolder} does not
   *                          have a title.
   */
  String getTitle();

  /**
   * @return the title of the current {@link IOptionalTitleHolder} in quotes.
   * @throws RuntimeException if the current {@link IOptionalTitleHolder} does not
   *                          have a title.
   */
  default String getTitleInQuotes() {
    return ("'" + getTitle() + "'");
  }

  /**
   * @return true if the current {@link IOptionalTitleHolder} has a title.
   */
  boolean hasTitle();
}
