package ch.nolix.coreapi.attributeapi.mutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalNameHolder;

/**
 * A {@link IMutableOptionalNameHolder} is a {@link IOptionalNameHolder} whose
 * name can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @version 2023-02-07
 */
public interface IMutableOptionalNameHolder extends IOptionalNameHolder {

  /**
   * Removes the name of the current {@link IMutableOptionalNameHolder}.
   */
  void removeName();

  /**
   * Sets the name of the current {@link IMutableOptionalNameHolder}.
   * 
   * @param name
   * @throws RuntimeException if the given name is null.
   * @throws RuntimeException if the given name is blank.
   */
  void setName(String name);
}
