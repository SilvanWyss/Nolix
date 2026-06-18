/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mandatoryattribute;

import ch.nolix.baseapi.commontype.stringtool.StringCatalog;

/**
 * A {@link INameHolder} has a name.
 * 
 * @author Silvan Wyss
 */
public interface INameHolder {
  /**
   * @return the name of the current {@link INameHolder}
   */
  String getName();

  /**
   * @return the name of the current {@link INameHolder} in single quotes
   */
  default String getNameInSingleQuotes() {
    return StringCatalog.SINGLE_QUOTE + getName() + StringCatalog.SINGLE_QUOTE;
  }

  /**
   * @param name
   * @return true if the current {@link INameHolder} has the given name, false
   *         otherwise
   */
  default boolean hasName(final String name) {
    return getName().equals(name);
  }

  /**
   * @param nameHolder
   * @return true if the current {@link INameHolder} has the same name as the
   *         given nameHolder, false otherwise
   */
  default boolean hasSameNameAs(final INameHolder nameHolder) {
    return nameHolder != null && getName().equals(nameHolder.getName());
  }
}
