//package declaration
package ch.nolix.coreapi.attributeapi.mandatoryattributeapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link ITitleHolder} has a title.
 * 
 * @author Silvan Wyss
 * @date 2019-07-26
 */
@AllowDefaultMethodsAsDesignPattern
public interface ITitleHolder {

  //method declaration
  /**
   * @return the title of the current {@link ITitleHolder}.
   */
  String getTitle();

  //method
  /**
   * @return the title of the current {@link ITitleHolder} in quotes.
   */
  default String getTitleInQuotes() {
    return ("'" + getTitle() + "'");
  }
}
