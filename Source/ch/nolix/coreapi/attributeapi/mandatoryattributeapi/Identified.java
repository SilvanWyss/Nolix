//package declaration
package ch.nolix.coreapi.attributeapi.mandatoryattributeapi;

import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link Identified} has an id.
 * 
 * @author Silvan Wyss
 * @date 2019-06-10
 */
@AllowDefaultMethodsAsDesignPattern
public interface Identified {

  // method declaration
  /**
   * @return the id of the current {@link Identified}.
   */
  String getId();

  // method
  /**
   * @return the id of the current {@link Identified} in quotes.
   */
  default String getIdInQuotes() {
    return ("'" + getId() + "'");
  }

  // method
  /**
   * @param id
   * @return true if the current {@link Identified} has the given id.
   */
  default boolean hasId(final String id) {
    return getId().equals(id);
  }
}
