/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mandatoryattribute;

import ch.nolix.baseapi.commontype.stringtool.StringCatalog;

/**
 * A {@link HeaderHolder} has a header.
 * 
 * @author Silvan Wyss
 */
public interface HeaderHolder {
  /**
   * @return the header of the current {@link HeaderHolder}
   */
  String getHeader();

  /**
   * @return the header of the current {@link HeaderHolder} in single quotes
   */
  default String getHeaderInSingleQuotes() {
    return StringCatalog.SINGLE_QUOTE + getHeader() + StringCatalog.SINGLE_QUOTE;
  }

  /**
   * @param header
   * @return true if the current {@link HeaderHolder} has the given header, false
   *         otherwise
   */
  default boolean hasHeader(final String header) {
    return getHeader().equals(header);
  }

  /**
   * @param headerHolder
   * @return true if the current {@link HeaderHolder} has the same header as the
   *         given headerHolder, false otherwise
   */
  default boolean hasSameHeaderAs(final HeaderHolder headerHolder) {
    return headerHolder != null && getHeader().equals(headerHolder.getHeader());
  }
}
