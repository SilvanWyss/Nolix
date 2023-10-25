//package declaration
package ch.nolix.coreapi.attributeapi.mandatoryattributeapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link IIndexHolder} has an index.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 */
@AllowDefaultMethodsAsDesignPattern
public interface IIndexHolder {

  //method declaration
  /**
   * @return the index of the current {@link IIndexHolder}.
   */
  int getIndex();

  //method
  /**
   * @return the index of the current {@link IIndexHolder} as {@link String}.
   */
  default String getIndexAsString() {
    return String.valueOf(getIndex());
  }

  //method
  /**
   * @return the index of the current {@link IIndexHolder} as {@link String} in
   *         quotes.
   */
  default String getIndexAsStringInQuotes() {
    return ("'" + getIndexAsString() + "'");
  }

  //method
  /**
   * @param index
   * @return true if the current {@link IIndexHolder} has the given index.
   */
  default boolean hasIndex(final int index) {
    return (getIndex() == index);
  }

  //method
  /**
   * @param object
   * @return true if the current {@link IIndexHolder} has the same index as the
   *         given object.
   */
  default boolean hasSameIndexAs(final IIndexHolder object) {

    //Handles the case that the given object is null.
    if (object == null) {
      return false;
    }

    //Handles the case that the given object is not null.
    return hasIndex(object.getIndex());
  }
}
