//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeapi;

//interface
/**
 * A {@link IOptionalNameHolder} can have a name.
 * 
 * @author Silvan Wyss
 * @date 2020-03-29
 */
public interface IOptionalNameHolder {

  //method declaration
  /**
   * @return the name of the current {@link IOptionalNameHolder}.
   */
  String getName();

  //method declaration
  /**
   * @return the name of the current {@link IOptionalNameHolder} in quotes.
   */
  String getNameInQuotes();

  //method declaration
  /**
   * @return true if the current {@link IOptionalNameHolder} has a name.
   */
  boolean hasName();

  //method
  /**
   * @param name
   * @return true if the current {@link IOptionalNameHolder} has the given name.
   */
  boolean hasName(String name);
}
