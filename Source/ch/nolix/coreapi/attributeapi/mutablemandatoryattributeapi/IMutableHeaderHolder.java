//package declaration
package ch.nolix.coreapi.attributeapi.mutablemandatoryattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IHeaderHolder;

//interface
/**
 * A {@link IMutableHeaderHolder} is a {@link IHeaderHolder} whose header can be
 * set programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-01-29
 */
public interface IMutableHeaderHolder extends IHeaderHolder {

  //method declaration
  /**
   * Sets the header of the current {@link IMutableHeaderHolder}.
   * 
   * @param header
   * @throws RuntimeException if the given header is null.
   * @throws RuntimeException if the given header is blank.
   */
  void setHeader(String header);
}
