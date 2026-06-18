/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.optionalattribute;

import java.util.Optional;

import ch.nolix.baseapi.commontype.stringtool.StringCatalog;

/**
 * A {@link IOptionalHeaderHolder} can have a header.
 * 
 * @author Silvan Wyss
 */
public interface IOptionalHeaderHolder {
  /**
   * @return the header of the current {@link IOptionalHeaderHolder}
   * @throws RuntimeException if the current {@link IOptionalHeaderHolder} does
   *                          not have a header
   */
  String getHeader();

  /**
   * @return the header of the current {@link IOptionalHeaderHolder} if the
   *         current {@link IOptionalHeaderHolder} has a header, an empty
   *         {@link String} otherwise
   */
  default String getHeaderOrEmptyString() {
    if (hasHeader()) {
      return getHeader();
    }

    return StringCatalog.EMPTY_STRING;
  }

  /**
   * @return a new {@link Optional} with the header of the current
   *         {@link IOptionalHeaderHolder} if the current
   *         {@link IOptionalHeaderHolder} has a header has a header, an empty
   *         {@link Optional} otherwise
   */
  default Optional<String> getOptionalHeader() {
    if (hasHeader()) {
      return Optional.of(getHeader());
    }

    return Optional.empty();
  }

  /**
   * @return true if the current {@link IOptionalHeaderHolder} has a header, false
   *         otherwise
   */
  boolean hasHeader();

  /**
   * @param header
   * @return true if the current {@link IOptionalHeaderHolder} has the given
   *         header, false otherwise
   */
  default boolean hasHeader(String header) {
    return hasHeader() && getHeader().equals(header);
  }
}
