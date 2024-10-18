package ch.nolix.coreapi.attributeapi.optionalattributeapi;

import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

/**
 * A {@link IOptionalIdHolder} can have an id.
 * 
 * @author Silvan Wyss
 * @version 2020-01-05
 */
@AllowDefaultMethodsAsDesignPattern
public interface IOptionalIdHolder {

  /**
   * @return the id of the current {@link IOptionalIdHolder}.
   */
  String getId();

  /**
   * @return the id of the current {@link IOptionalIdHolder} in quotes.
   */
  default String getIdInQuotes() {
    return ("'" + getId() + "'");
  }

  /**
   * @return true if the current {@link IOptionalIdHolder} has an id.
   */
  boolean hasId();

  /**
   * @param id
   * @return true if the current {@link IOptionalIdHolder} has the given id.
   */
  default boolean hasId(final String id) {
    return hasId()
    && getId().equals(id);
  }
}
