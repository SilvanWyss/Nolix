package ch.nolix.coreapi.attributeapi.mutableoptionalattributeapi;

import ch.nolix.coreapi.attributeapi.optionalattributeapi.IOptionalTextHolder;

/**
 * A {@link IMutableOptionalTextHolder} is a {@link IOptionalTextHolder} whose
 * text can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @version 2023-02-09
 */
public interface IMutableOptionalTextHolder extends IOptionalTextHolder {

  /**
   * Removes the text of the current {@link IMutableOptionalTextHolder}.
   */
  void removeText();

  /**
   * Sets the text of the current {@link IMutableOptionalTextHolder}.
   * 
   * @param text
   * @throws RuntimeException if the given text is null.
   */
  void setText(String text);
}
