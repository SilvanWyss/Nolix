/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mandatoryattribute;

import ch.nolix.baseapi.commontypetool.stringtool.StringCatalog;

/**
 * A {@link IHeaderHolder} has a header.
 * 
 * @author Silvan Wyss
 */
public interface IHeaderHolder {
  /**
   * @return the header of the current {@link IHeaderHolder}
   */
  String getHeader();

  /**
   * @return the header of the current {@link IHeaderHolder} in single quotes
   */
  default String getHeaderInSingleQuotes() {
    return StringCatalog.SINGLE_QUOTE + getHeader() + StringCatalog.SINGLE_QUOTE;
  }

  /**
   * @param header
   * @return true if the current {@link IHeaderHolder} has the given header, false
   *         otherwise
   */
  default boolean hasHeader(final String header) {
    return getHeader().equals(header);
  }

  /**
   * @param headerHolder
   * @return true if the current {@link IHeaderHolder} has the same header as the
   *         given headerHolder, false otherwise
   */
  default boolean hasSameHeaderAs(final IHeaderHolder headerHolder) {
    return headerHolder != null && getHeader().equals(headerHolder.getHeader());
  }
}
