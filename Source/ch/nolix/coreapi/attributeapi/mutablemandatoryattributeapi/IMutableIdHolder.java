//package declaration
package ch.nolix.coreapi.attributeapi.mutablemandatoryattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IIdHolder;

//interface
/**
 * A {@link IMutableIdHolder} is a {@link IIdHolder} whose id can be set
 * programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-02-09
 */
public interface IMutableIdHolder extends IIdHolder {

  //method declaration
  /**
   * Sets the id of the current {@link IMutableIdHolder}.
   * 
   * @param id
   * @throws RuntimeException if the given id is null.
   * @throws RuntimeException if the given id is blank.
   */
  void setId(String id);
}
