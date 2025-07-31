package ch.nolix.coreapi.attribute.optionalattribute;

import java.util.Optional;

/**
 * A {@link IOptionalHeaderHolder} can have a header.
 * 
 * @author Silvan Wyss
 * @version 2020-03-28
 */
public interface IOptionalHeaderHolder {

  /**
   * @return the header of the current {@link IOptionalHeaderHolder}.
   * @throws RuntimeException if the current {@link IOptionalHeaderHolder} does
   *                          not have a header.
   */
  String getHeader();

  /**
   * @return the header of the current {@link IOptionalHeaderHolder} if it has a
   *         header, otherwise an empty {@link String}.
   */
  default String getHeaderOrEmptyString() {

    if (!hasHeader()) {
      return "";
    }

    return getHeader();
  }

  /**
   * @return a new {@link Optional} with the header of the current
   *         {@link IOptionalHeaderHolder} if it has a header, otherwise an empty
   *         {@link Optional}.
   */
  default Optional<String> getOptionalHeader() {

    if (hasHeader()) {
      return Optional.of(getHeader());
    }

    return Optional.empty();
  }

  /**
   * @return true if the current {@link IOptionalHeaderHolder} has a header.
   */
  boolean hasHeader();

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
