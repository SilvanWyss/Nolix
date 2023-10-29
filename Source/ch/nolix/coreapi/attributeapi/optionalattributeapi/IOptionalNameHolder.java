//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link IOptionalNameHolder} can have a name.
 * 
 * @author Silvan Wyss
 * @date 2020-03-29
 */
@AllowDefaultMethodsAsDesignPattern
public interface IOptionalNameHolder {

  //method declaration
  /**
   * @return the name of the current {@link IOptionalNameHolder}.
   */
  String getName();

  //method
  /**
   * @return the name of the current {@link IOptionalNameHolder} in quotes.
   */
  default String getNameInQuotes() {
    return ("'" + getName() + "'");
  }

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
  default boolean hasName(final String name) {
    return hasName()
    && getName().equals(name);
  }
}
