//package declaration
package ch.nolix.coreapi.attributeapi.mandatoryattributeapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link INameHolder} has a name.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 */
@AllowDefaultMethodsAsDesignPattern
public interface INameHolder {

  //method declaration
  /**
   * @return the name of the current {@link INameHolder}.
   */
  String getName();

  //method
  /**
   * @return the name of the current {@link INameHolder} in quotes.
   */
  default String getNameInQuotes() {
    return ("'" + getName() + "'");
  }

  //method
  /**
   * @param name
   * @return true if the current {@link INameHolder} has the given name.
   */
  default boolean hasName(final String name) {
    return getName().equals(name);
  }

  //method
  /**
   * @param object
   * @return true if the current {@link INameHolder} has the same name as the
   *         given object.
   */
  default boolean hasSameNameAs(final INameHolder object) {

    //Handles the case that the given object is null.
    if (object == null) {
      return false;
    }

    //Handles the case that the given object is not null.
    return hasName(object.getName());
  }
}
