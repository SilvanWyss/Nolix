/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.optionalattribute;

import ch.nolix.baseapi.commontype.stringtool.StringCatalog;

/**
 * A {@link OptionalIdHolder} can have an id.
 * 
 * @author Silvan Wyss
 */
public interface OptionalIdHolder {
  /**
   * @return the id of the current {@link OptionalIdHolder}
   * @throws RuntimeException if the current {@link OptionalIdHolder} does not
   *                          have an id
   */
  String getId();

  /**
   * @return the id of the current {@link OptionalIdHolder} in single quotes
   * @throws RuntimeException if the current {@link OptionalIdHolder} does not
   *                          have an id
   */
  default String getIdInSingleQuotes() {
    return StringCatalog.SINGLE_QUOTE + getId() + StringCatalog.SINGLE_QUOTE;
  }

  /**
   * @return true if the current {@link OptionalIdHolder} has an id, false
   *         otherwise
   */
  boolean hasId();

  /**
   * @param id
   * @return true if the current {@link OptionalIdHolder} has the given id, false
   *         otherwise
   */
  default boolean hasId(final String id) {
    return hasId() && getId().equals(id);
  }
}
