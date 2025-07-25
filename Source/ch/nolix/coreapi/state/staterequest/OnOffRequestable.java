package ch.nolix.coreapi.state.staterequest;

/**
 * A {@link OnOffRequestable} can be asked if it is on or off.
 * 
 * @author Silvan Wyss
 * @version 2023-02-05
 */
public interface OnOffRequestable {

  /**
   * @return true if the current {@link OnOffRequestable} is off, false otherwise.
   */
  boolean isOff();

  /**
   * @return true if the current {@link OnOffRequestable} is on, false otherwise.
   */
  boolean isOn();

}
