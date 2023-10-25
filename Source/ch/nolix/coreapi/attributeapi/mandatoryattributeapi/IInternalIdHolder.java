//package declaration
package ch.nolix.coreapi.attributeapi.mandatoryattributeapi;

//own imports
import ch.nolix.coreapi.programstructureapi.markerapi.AllowDefaultMethodsAsDesignPattern;

//interface
/**
 * A {@link IInternalIdHolder} has an internal id.
 * 
 * @author Silvan Wyss
 * @date 2022-08-26
 */
@AllowDefaultMethodsAsDesignPattern
public interface IInternalIdHolder {

  //method declaration
  /**
   * @return the internal id of the current {@link IInternalIdHolder}.
   */
  String getInternalId();

  //method
  /**
   * @param internalId
   * @return true if the current {@link IInternalIdHolder} has the given
   *         internalId.
   */
  default boolean hasInternalId(final String internalId) {
    return getInternalId().equals(internalId);
  }
}
