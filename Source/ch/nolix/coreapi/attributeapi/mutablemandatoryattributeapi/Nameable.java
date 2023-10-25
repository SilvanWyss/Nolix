//package declaration
package ch.nolix.coreapi.attributeapi.mutablemandatoryattributeapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;

//interface
/**
 * A {@link Nameable} is a {@link INameHolder} whose name can be set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-01-27
 */
public interface Nameable extends INameHolder {

  //method declaration
  /**
   * Sets the name of the current {@link Nameable}.
   * 
   * @param name
   * @throws RuntimeException if the given name is null.
   */
  void setName(String name);
}
