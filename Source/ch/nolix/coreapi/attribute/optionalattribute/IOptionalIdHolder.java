/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.attribute.optionalattribute;

/**
 * A {@link IOptionalIdHolder} can have an id.
 * 
 * @author Silvan Wyss
 */
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
