//package declaration
package ch.nolix.coreapi.attributeapi.mutablemandatoryattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.INameHolder;

//interface
/**
 * A {@link IMutableNameHolder} is a {@link INameHolder} whose name can be set
 * programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-01-27
 */
public interface IMutableNameHolder extends INameHolder {

  //method declaration
  /**
   * Sets the name of the current {@link IMutableNameHolder}.
   * 
   * @param name
   * @throws RuntimeException if the given name is null.
   * @throws RuntimeException if the given name is blank.
   */
  void setName(String name);
}
