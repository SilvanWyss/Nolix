//package declaration
package ch.nolix.coreapi.stateapi.staterequestapi;

//interface
/**
 * A {@link BlanknessRequestable} can be asked if it is blank.
 * 
 * @author Silvan Wyss
 * @version 2022-07-17
 */
public interface BlanknessRequestable {

  //method declaration
  /**
   * @return true if {@link BlanknessRequestable} is blank.
   */
  boolean isBlank();
}
