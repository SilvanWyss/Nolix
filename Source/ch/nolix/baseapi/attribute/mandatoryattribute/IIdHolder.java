/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mandatoryattribute;

/**
 * A {@link IIdHolder} has an id.
 * 
 * @author Silvan Wyss
 */
public interface IIdHolder {
  /**
   * @return the id of the current {@link IIdHolder}.
   */
  String getId();

  /**
   * @return the id of the current {@link IIdHolder} in quotes.
   */
  default String getIdInQuotes() {
    return ("'" + getId() + "'");
  }

  /**
   * @param id
   * @return true if the current {@link IIdHolder} has the given id, false
   *         otherwise.
   */
  default boolean hasId(final String id) {
    return getId().equals(id);
  }
}
