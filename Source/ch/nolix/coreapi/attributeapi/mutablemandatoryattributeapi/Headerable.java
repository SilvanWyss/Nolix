//package declaration
package ch.nolix.coreapi.attributeapi.mutablemandatoryattributeapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Headered;

//interface
/**
 * A {@link Headerable} is a {@link Headered} whose header can be set
 * programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-01-29
 */
public interface Headerable extends Headered {

  //method declaration
  /**
   * Sets the header of the current {@link Headerable}.
   * 
   * @param header
   * @throws RuntimeException if the given header is null.
   * @throws RuntimeException if the given header is blank.
   */
  void setHeader(String header);
}
