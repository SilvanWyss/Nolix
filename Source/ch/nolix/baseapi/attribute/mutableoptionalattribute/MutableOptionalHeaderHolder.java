/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.OptionalHeaderHolder;

/**
 * A {@link MutableOptionalHeaderHolder} is a {@link OptionalHeaderHolder}
 * whose header can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 */
public interface MutableOptionalHeaderHolder extends OptionalHeaderHolder {
  /**
   * Removes the header of the current {@link MutableOptionalHeaderHolder}.
   */
  void removeHeader();

  /**
   * Sets the header of the current {@link MutableOptionalHeaderHolder}.
   * 
   * @param header
   * @throws RuntimeException if the given header is null or blank.
   */
  void setHeader(String header);
}
