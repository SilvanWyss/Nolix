package ch.nolix.coreapi.state.staterequest;

/**
 * A {@link BlanknessRequestable} can be asked if it is blank.
 * 
 * @author Silvan Wyss
 */
public interface BlanknessRequestable {
  /**
   * @return true if {@link BlanknessRequestable} is blank, false otherwise.
   */
  boolean isBlank();
}
