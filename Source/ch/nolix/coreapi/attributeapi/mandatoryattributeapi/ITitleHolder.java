package ch.nolix.coreapi.attributeapi.mandatoryattributeapi;

import ch.nolix.coreapi.structureapi.typemarkerapi.AllowDefaultMethodsAsDesignPattern;

/**
 * A {@link ITitleHolder} has a title.
 * 
 * @author Silvan Wyss
 * @version 2019-07-26
 */
@AllowDefaultMethodsAsDesignPattern
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
