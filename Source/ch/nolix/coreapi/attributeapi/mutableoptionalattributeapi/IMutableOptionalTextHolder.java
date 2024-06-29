//package declaration
package ch.nolix.coreapi.attributeapi.mutableoptionalattributeapi;

//own imports
import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalTextHolder;

//interface
/**
 * A {@link IMutableOptionalTextHolder} is a {@link IOptionalTextHolder} whose
 * text can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @version 2023-02-09
 */
public interface IMutableOptionalTextHolder extends IOptionalTextHolder {

  //method declaration
  /**
   * Removes the text of the current {@link IMutableOptionalTextHolder}.
   */
  void removeText();

  //method declaration
  /**
   * Sets the text of the current {@link IMutableOptionalTextHolder}.
   * 
   * @param text
   * @throws RuntimeException if the given text is null.
   */
  void setText(String text);
}
