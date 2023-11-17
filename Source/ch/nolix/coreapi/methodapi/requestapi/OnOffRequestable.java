//package declaration
package ch.nolix.coreapi.methodapi.requestapi;

//interface
/**
 * A {@link OnOffRequestable} can be asked if it is on or off.
 * 
 * @author Silvan Wyss
 * @date 2023-02-05
 */
public interface OnOffRequestable {

  //method declaration
  /**
   * @return true if the current {@link OnOffRequestable} is off, false otherwise.
   */
  boolean isOff();

  //method declaration
  /**
   * @return true if the current {@link OnOffRequestable} is on, false otherwise.
   */
  boolean isOn();

}
