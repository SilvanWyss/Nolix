//package declaration
package ch.nolix.coreapi.attributeapi.mutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.optionalattributeapi.OptionalHeadered;

//interface
/**
 * A {@link OptionalHeaderable} is a {@link OptionalHeadered} whose header can
 * be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-02-07
 */
public interface OptionalHeaderable extends OptionalHeadered {

  // method declaration
  /**
   * Removes the header of the current {@link OptionalHeaderable}.
   */
  void removeHeader();

  // method declaration
  /**
   * Sets the header of the current {@link OptionalHeaderable}.
   * 
   * @param header
   * @throws RuntimeException if the given header is null.
   * @throws RuntimeException if the given header is blank.
   */
  void setHeader(String header);
}
