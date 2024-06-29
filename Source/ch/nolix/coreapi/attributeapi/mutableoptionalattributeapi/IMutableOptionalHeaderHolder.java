//package declaration
package ch.nolix.coreapi.attributeapi.mutableoptionalattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalHeaderHolder;

//interface
/**
 * A {@link IMutableOptionalHeaderHolder} is a {@link IOptionalHeaderHolder}
 * whose header can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @version 2023-02-07
 */
public interface IMutableOptionalHeaderHolder extends IOptionalHeaderHolder {

  //method declaration
  /**
   * Removes the header of the current {@link IMutableOptionalHeaderHolder}.
   */
  void removeHeader();

  //method declaration
  /**
   * Sets the header of the current {@link IMutableOptionalHeaderHolder}.
   * 
   * @param header
   * @throws RuntimeException if the given header is null.
   * @throws RuntimeException if the given header is blank.
   */
  void setHeader(String header);
}
