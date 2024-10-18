package ch.nolix.coreapi.attributeapi.optionalattributeapi;

import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

/**
 * A {@link IOptionalNameHolder} can have a name.
 * 
 * @author Silvan Wyss
 * @version 2020-03-29
 */
@AllowDefaultMethodsAsDesignPattern
public interface IOptionalNameHolder {

  /**
   * @return the name of the current {@link IOptionalNameHolder}.
   */
  String getName();

  /**
   * @return the name of the current {@link IOptionalNameHolder} in quotes.
   */
  default String getNameInQuotes() {
    return ("'" + getName() + "'");
  }

  /**
   * @return true if the current {@link IOptionalNameHolder} has a name.
   */
  boolean hasName();

  /**
   * @param name
   * @return true if the current {@link IOptionalNameHolder} has the given name.
   */
  default boolean hasName(final String name) {
    return hasName()
    && getName().equals(name);
  }
}
