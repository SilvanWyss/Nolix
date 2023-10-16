//package declaration
package ch.nolix.coreapi.attributeapi.mutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.ITextHolder;

//interface
/**
 * A {@link IMutableOptionalTextHolder} is a {@link ITextHolder} whose text can
 * be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @date 2023-02-09
 */
public interface IMutableOptionalTextHolder extends ITextHolder {

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
