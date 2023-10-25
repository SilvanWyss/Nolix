//package declaration
package ch.nolix.coreapi.attributeapi.mandatoryattributeapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link IHeaderHolder} has a header.
 * 
 * @author Silvan Wyss
 * @date 2018-04-07
 */
@AllowDefaultMethodsAsDesignPattern
public interface IHeaderHolder {

  //method declaration
  /**
   * @return the header of the current {@link IHeaderHolder}.
   */
  String getHeader();

  //method
  /**
   * @return the header of the current {@link IHeaderHolder} in quotes.
   */
  default String getHeaderInQuotes() {
    return ("'" + getHeader() + "'");
  }

  //method
  /**
   * @param header
   * @return true if the current {@link IHeaderHolder} has the given header.
   */
  default boolean hasHeader(final String header) {
    return getHeader().equals(header);
  }

  //method
  /**
   * @param object
   * @return true if the current {@link IHeaderHolder} has the same header as the
   *         given object.
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