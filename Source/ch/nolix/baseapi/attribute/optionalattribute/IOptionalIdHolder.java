/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.optionalattribute;

import ch.nolix.baseapi.commontypetool.stringtool.StringCatalog;

/**
 * A {@link IOptionalIdHolder} can have an id.
 * 
 * @author Silvan Wyss
 */
public interface IOptionalIdHolder {
  /**
   * @return the id of the current {@link IOptionalIdHolder}
   * @throws RuntimeException if the current {@link IOptionalIdHolder} does not
   *                          have an id
   */
  String getId();

  /**
   * @return the id of the current {@link IOptionalIdHolder} in single quotes
   * @throws RuntimeException if the current {@link IOptionalIdHolder} does not
   *                          have an id
   */
  default String getIdInSingleQuotes() {
    return StringCatalog.SINGLE_QUOTE + getId() + StringCatalog.SINGLE_QUOTE;
  }

  /**
   * @return true if the current {@link IOptionalIdHolder} has an id, false
   *         otherwise
   */
  boolean hasId();

  /**
   * @param id
   * @return true if the current {@link IOptionalIdHolder} has the given id, false
   *         otherwise
   */
  default boolean hasId(final String id) {
    return hasId() && getId().equals(id);
  }
}
