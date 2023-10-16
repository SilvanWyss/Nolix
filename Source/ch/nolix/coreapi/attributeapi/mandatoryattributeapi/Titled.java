//package declaration
package ch.nolix.coreapi.attributeapi.mandatoryattributeapi;

import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link Titled} has a title.
 * 
 * @author Silvan Wyss
 * @date 2019-07-26
 */
@AllowDefaultMethodsAsDesignPattern
public interface Titled {

  //method declaration
  /**
   * @return the title of the current {@link Titled}.
   */
  String getTitle();

  //method
  /**
   * @return the title of the current {@link Titled} in quotes.
   */
  String getTitleInQuotes();
}
