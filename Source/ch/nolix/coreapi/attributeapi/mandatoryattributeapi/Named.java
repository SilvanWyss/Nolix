//package declaration
package ch.nolix.coreapi.attributeapi.mandatoryattributeapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link Named} has a name.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 */
@AllowDefaultMethodsAsDesignPattern
public interface Named {

  // method declaration
  /**
   * @return the name of the current {@link Named}.
   */
  String getName();

  // method
  /**
   * @return the name of the current {@link Named} in quotes.
   */
  default String getNameInQuotes() {
    return ("'" + getName() + "'");
  }

  // method
  /**
   * @param name
   * @return true if the current {@link Named} has the given name.
   */
  default boolean hasName(final String name) {
    return getName().equals(name);
  }

  // method
  /**
   * @param object
   * @return true if the current {@link Named} has the same name as the given
   *         object.
   */
  default boolean hasSameNameAs(final Named object) {

    // Handles the case that the given object is null.
    if (object == null) {
      return false;
    }

    // Handles the case that the given object is not null.
    return hasName(object.getName());
  }
}
