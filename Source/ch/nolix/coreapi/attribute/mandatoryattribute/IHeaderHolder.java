package ch.nolix.coreapi.attribute.mandatoryattribute;

/**
 * A {@link IHeaderHolder} has a header.
 * 
 * @author Silvan Wyss
 * @version 2018-04-07
 */
public interface IHeaderHolder {
  /**
   * @return the header of the current {@link IHeaderHolder}.
   */
  String getHeader();

  /**
   * @return the header of the current {@link IHeaderHolder} in quotes.
   */
  default String getHeaderInQuotes() {
    return ("'" + getHeader() + "'");
  }

  /**
   * @param header
   * @return true if the current {@link IHeaderHolder} has the given header, false
   *         otherwise.
   */
  default boolean hasHeader(final String header) {
    return getHeader().equals(header);
  }

  /**
   * @param object
   * @return true if the current {@link IHeaderHolder} has the same header as the
   *         given object, false otherwise.
   */
  default boolean hasSameHeaderAs(final IHeaderHolder object) {
    //Handles the case that the given object is null.
    if (object == null) {
      return false;
    }

    //Handles the case that the given object is not null.
    return getHeader().equals(object.getHeader());
  }
}
