/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.state.staterequest;

/**
 * A {@link OnOffRequestable} can be asked if it is on or off.
 * 
 * @author Silvan Wyss
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
