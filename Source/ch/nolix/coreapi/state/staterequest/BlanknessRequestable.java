package ch.nolix.coreapi.state.staterequest;

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
