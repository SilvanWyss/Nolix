//package declaration
package ch.nolix.coreapi.attributeapi.mandatoryattributeapi;

//interface
/**
 * A {@link InternalIdentified} has an internal id.
 * 
 * @author Silvan Wyss
 * @date 2022-08-26
 */
public interface InternalIdentified {

  // method declaration
  /**
   * @return the internal id of the current {@link InternalIdentified}.
   */
  String getInternalId();

  // method declaration
  /**
   * @param internalId
   * @return true if the current {@link InternalIdentified} has the given
   *         internalId.
   */
  boolean hasInternalId(String internalId);
}
