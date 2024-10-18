package ch.nolix.coreapi.stateapi.staterequestapi;

/**
 * A {@link BlanknessRequestable} can be asked if it is blank.
 * 
 * @author Silvan Wyss
 * @version 2022-07-17
 */
public interface BlanknessRequestable {

  /**
   * @return true if {@link BlanknessRequestable} is blank.
   */
  boolean isBlank();
}
