/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mandatoryattribute;

import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;

/**
 * A {@link NameHolder} has a name.
 * 
 * @author Silvan Wyss
 */
public interface NameHolder {
  /**
   * @return the name of the current {@link NameHolder}
   */
  String getName();

  /**
   * @return the name of the current {@link NameHolder} in single quotes
   */
  default String getNameInSingleQuotes() {
    return StringCatalog.SINGLE_QUOTE + getName() + StringCatalog.SINGLE_QUOTE;
  }

  /**
   * @param name
   * @return true if the current {@link NameHolder} has the given name, false
   *         otherwise
   */
  default boolean hasName(final String name) {
    return getName().equals(name);
  }

  /**
   * @param nameHolder
   * @return true if the current {@link NameHolder} has the same name as the
   *         given nameHolder, false otherwise
   */
  default boolean hasSameNameAs(final NameHolder nameHolder) {
    return nameHolder != null && getName().equals(nameHolder.getName());
  }
}
