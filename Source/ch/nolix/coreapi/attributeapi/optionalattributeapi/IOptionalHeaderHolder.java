//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link IOptionalHeaderHolder} can have a header.
 * 
 * @author Silvan Wyss
 * @date 2020-03-28
 */
@AllowDefaultMethodsAsDesignPattern
public interface IOptionalHeaderHolder {

  //method declaration
  /**
   * @return the header of the current {@link IOptionalHeaderHolder}.
   * @throws RuntimeException if the current {@link IOptionalHeaderHolder} does
   *                          not have a header.
   */
  String getHeader();

  //method
  /**
   * @return the header of the current {@link IOptionalHeaderHolder} if it has a
   *         header, otherwise an empty {@link String}.
   */
  String getHeaderOrEmptyString();

  //method declaration
  /**
   * @return the header of the current {@link IOptionalHeaderHolder} if the
   *         current {@link IOptionalHeaderHolder} has a header, null otherwise.
   */
  String getHeaderOrNull();

  //method declaration
  /**
   * @return true if the current {@link IOptionalHeaderHolder} has a header.
   */
  boolean hasHeader();

  //method
  /**
   * @param header
   * @return true if the current {@link IOptionalHeaderHolder} has the given
   *         header.
   */
  default boolean hasHeader(String header) {
    return hasHeader()
        && getHeader().equals(header);
  }
}
