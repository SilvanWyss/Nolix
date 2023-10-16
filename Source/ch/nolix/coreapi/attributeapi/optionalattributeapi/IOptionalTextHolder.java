//package declaration
package ch.nolix.coreapi.attributeapi.optionalattributeapi;

//interface
/**
 * A {@link IOptionalTextHolder} can have a text.
 * 
 * @author Silvan Wyss
 * @date 2023-02-03
 */
public interface IOptionalTextHolder {

  //method declaration
  /**
   * @return the text of the current {@link IOptionalTextHolder}.
   * @throws RuntimeException if the current {@link IOptionalTextHolder} does not
   *                          have a text.
   */
  String getText();

  //method declaration
  /**
   * @return true if the current {@link IOptionalTextHolder} has a text.
   */
  boolean hasText();
}
