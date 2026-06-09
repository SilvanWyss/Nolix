/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mandatoryattribute;

import ch.nolix.baseapi.commontypetool.stringtool.StringCatalog;

/**
 * A {@link IIdHolder} has an id.
 * 
 * @author Silvan Wyss
 */
public interface IIdHolder {
  /**
   * @return the id of the current {@link IIdHolder}
   */
  String getId();

  /**
   * @return the id of the current {@link IIdHolder} in single quotes
   */
  default String getIdInSingleQuotes() {
    return StringCatalog.SINGLE_QUOTE + getId() + StringCatalog.SINGLE_QUOTE;
  }

  /**
   * @param id
   * @return true if the current {@link IIdHolder} has the given id, false
   *         otherwise
   */
  default boolean hasId(final String id) {
    return getId().equals(id);
  }
}
