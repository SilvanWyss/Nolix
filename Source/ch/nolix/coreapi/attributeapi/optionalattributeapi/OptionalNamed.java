//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeapi;

//interface
/**
 * A {@link OptionalNamed} can have a name.
 * 
 * @author Silvan Wyss
 * @date 2020-03-29
 */
public interface OptionalNamed {

  //method declaration
  /**
   * @return the name of the current {@link OptionalNamed}.
   */
  String getName();

  //method declaration
  /**
   * @return the name of the current {@link OptionalNamed} in quotes.
   */
  String getNameInQuotes();

  //method declaration
  /**
   * @return true if the current {@link OptionalNamed} has a name.
   */
  boolean hasName();

  //method
  /**
   * @param name
   * @return true if the current {@link OptionalNamed} has the given name.
   */
  boolean hasName(String name);
}
