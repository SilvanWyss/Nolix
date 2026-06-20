/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mandatoryattribute;

import ch.nolix.baseapi.commontype.stringtool.StringCatalog;

/**
 * A {@link IdHolder} has an id.
 * 
 * @author Silvan Wyss
 */
public interface IdHolder {
  /**
   * @return the id of the current {@link IdHolder}
   */
  String getId();

  /**
   * @return the id of the current {@link IdHolder} in single quotes
   */
  default String getIdInSingleQuotes() {
    return StringCatalog.SINGLE_QUOTE + getId() + StringCatalog.SINGLE_QUOTE;
  }

  /**
   * @param id
   * @return true if the current {@link IdHolder} has the given id, false
   *         otherwise
   */
  default boolean hasId(final String id) {
    return getId().equals(id);
  }
}
