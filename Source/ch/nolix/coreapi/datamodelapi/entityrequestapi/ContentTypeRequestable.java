//package declaration
package ch.nolix.coreapi.datamodelapi.entityrequestapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link ContentTypeRequestable} can be asked if it either is for holding
 * values or references.
 * 
 * @author Silvan Wyss
 * @date 2023-09-08
 */
@AllowDefaultMethodsAsDesignPattern
public interface ContentTypeRequestable {

  //method
  /**
   * @return true if the current {@link ContentTypeRequestable} is for references,
   *         false otherwise.
   */
  default boolean isForReferences() {
    return !isForValues();
  }

  //method declaration
  /**
   * @return true if the current {@link ContentTypeRequestable} is for values,
   *         false otherwiese.
   */
  boolean isForValues();
}
