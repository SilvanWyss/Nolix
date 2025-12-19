package ch.nolix.coreapi.attribute.mutableoptionalattribute;

import ch.nolix.coreapi.attribute.optionalattribute.IOptionalTextHolder;

/**
 * A {@link IMutableOptionalTextHolder} is a {@link IOptionalTextHolder} whose
 * text can be set and removed programmatically.
 * 
 * @author Silvan Wyss
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
