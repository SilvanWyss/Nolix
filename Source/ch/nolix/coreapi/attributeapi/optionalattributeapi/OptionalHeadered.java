//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeapi;

//interface
/**
 * A {@link OptionalHeadered} can have a header.
 * 
 * @author Silvan Wyss
 * @date 2020-03-28
 */
public interface OptionalHeadered {

  // method declaration
  /**
   * @return the header of the current {@link OptionalHeadered}.
   */
  String getHeader();

  // method
  /**
   * @return the header of the current {@link OptionalHeadered} if it has a
   *         header, otherwise an empty {@link String}.
   */
  String getHeaderOrEmptyString();

  // method declaration
  /**
   * @return the header of the current {@link OptionalHeadered} if the current
   *         {@link OptionalHeadered} has a header, null otherwise.
   */
  String getHeaderOrNull();

  // method declaration
  /**
   * @return true if the current {@link OptionalHeadered} has a header.
   */
  boolean hasHeader();

  // method
  /**
   * @param header
   * @return true if the current {@link OptionalHeadered} has the given header.
   */
  boolean hasHeader(String header);
}
