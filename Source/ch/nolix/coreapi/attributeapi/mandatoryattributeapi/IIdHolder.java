//package declaration
package ch.nolix.coreapi.attributeapi.mandatoryattributeapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link IIdHolder} has an id.
 * 
 * @author Silvan Wyss
 * @date 2019-06-10
 */
@AllowDefaultMethodsAsDesignPattern
public interface IIdHolder {

  //method declaration
  /**
   * @return the id of the current {@link IIdHolder}.
   */
  String getId();

  //method
  /**
   * @return the id of the current {@link IIdHolder} in quotes.
   */
  default String getIdInQuotes() {
    return ("'" + getId() + "'");
  }

  //method
  /**
   * @param id
   * @return true if the current {@link IIdHolder} has the given id.
   */
  default boolean hasId(final String id) {
    return getId().equals(id);
  }
}
