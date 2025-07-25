package ch.nolix.coreapi.attribute.mutableoptionalattribute;

import ch.nolix.coreapi.attribute.optionalattribute.IOptionalHeaderHolder;

/**
 * A {@link IMutableOptionalHeaderHolder} is a {@link IOptionalHeaderHolder}
 * whose header can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @version 2023-02-07
 */
public interface IMutableOptionalHeaderHolder extends IOptionalHeaderHolder {

  /**
   * Removes the header of the current {@link IMutableOptionalHeaderHolder}.
   */
  void removeHeader();

  /**
   * Sets the header of the current {@link IMutableOptionalHeaderHolder}.
   * 
   * @param header
   * @throws RuntimeException if the given header is null.
   * @throws RuntimeException if the given header is blank.
   */
  void setHeader(String header);
}
