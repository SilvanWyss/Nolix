//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link IOptionalIdHolder} can have an id.
 * 
 * @author Silvan Wyss
 * @date 2020-01-05
 */
@AllowDefaultMethodsAsDesignPattern
public interface IOptionalIdHolder {

  //method declaration
  /**
   * @return the id of the current {@link IOptionalIdHolder}.
   */
  String getId();

  //method
  /**
   * @return the id of the current {@link IOptionalIdHolder} in quotes.
   */
  default String getIdInQuotes() {
    return ("'" + getId() + "'");
  }

  //method declaration
  /**
   * @return true if the current {@link IOptionalIdHolder} has an id.
   */
  boolean hasId();

  //method
  /**
   * @param id
   * @return true if the current {@link IOptionalIdHolder} has the given id.
   */
  default boolean hasId(final String id) {
    return hasId()
    && getId().equals(id);
  }
}
