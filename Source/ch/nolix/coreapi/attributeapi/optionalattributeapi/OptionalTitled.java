//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeapi;

//interface
/**
 * A {@link OptionalTitled} can have a title.
 * 
 * @author Silvan Wyss
 * @date 2023-02-06
 */
public interface OptionalTitled {

  //method declaration
  /**
   * @return the title of the current {@link OptionalTitled}.
   */
  String getTitle();

  //method declaration
  /**
   * @return the title of the current {@link OptionalTitled} in quotes.
   */
  String getTitleInQuotes();

  //method declaration
  /**
   * @return true if the current {@link OptionalTitled} has a title.
   */
  boolean hasTitle();
}
