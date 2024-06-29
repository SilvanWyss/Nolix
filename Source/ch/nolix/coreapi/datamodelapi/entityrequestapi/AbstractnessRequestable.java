//package declaration
package ch.nolix.coreapi.datamodelapi.entityrequestapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link AbstractnessRequestable} can be asked if it is either abstract or
 * concrete.
 * 
 * @author Silvan Wyss
 * @version 2023-08-25
 */
@AllowDefaultMethodsAsDesignPattern
public interface AbstractnessRequestable {

  //method declaration
  /**
   * @return true if the current {@link AbstractnessRequestable} is abstract,
   *         false otherwise.
   */
  boolean isAbstract();

  //method
  /**
   * @return true if the current {@link AbstractnessRequestable} is concreate,
   *         false otherwise.
   */
  default boolean isConcrete() {
    return !isAbstract();
  }
}
