//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeapi;

//interface
/**
 * A {@link IOptionalTitleHolder} can have a title.
 * 
 * @author Silvan Wyss
 * @date 2023-02-06
 */
public interface IOptionalTitleHolder {

  //method declaration
  /**
   * @return the title of the current {@link IOptionalTitleHolder}.
   */
  String getTitle();

  //method declaration
  /**
   * @return the title of the current {@link IOptionalTitleHolder} in quotes.
   */
  String getTitleInQuotes();

  //method declaration
  /**
   * @return true if the current {@link IOptionalTitleHolder} has a title.
   */
  boolean hasTitle();
}
