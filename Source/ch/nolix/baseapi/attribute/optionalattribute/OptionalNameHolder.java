/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.optionalattribute;

import ch.nolix.baseapi.commontype.stringtool.StringCatalog;

/**
 * A {@link OptionalNameHolder} can have a name.
 * 
 * @author Silvan Wyss
 */
public interface OptionalNameHolder {
  /**
   * @return the name of the current {@link OptionalNameHolder}
   * @throws RuntimeException if the current {@link OptionalNameHolder} does not
   *                          have a name
   */
  String getName();

  /**
   * @return the name of the current {@link OptionalNameHolder} in single quotes
   * @throws RuntimeException if the current {@link OptionalNameHolder} does not
   *                          have a name
   */
  default String getNameInSingleQuotes() {
    return StringCatalog.SINGLE_QUOTE + getName() + StringCatalog.SINGLE_QUOTE;
  }

  /**
   * @return true if the current {@link OptionalNameHolder} has a name, false
   *         otherwise
   */
  boolean hasName();

  /**
   * @param name
   * @return true if the current {@link OptionalNameHolder} has the given name,
   *         false otherwise
   */
  default boolean hasName(final String name) {
    return hasName() && getName().equals(name);
  }
}
