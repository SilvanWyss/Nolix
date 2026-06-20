/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.OptionalTextHolder;

/**
 * A {@link MutableOptionalTextHolder} is a {@link OptionalTextHolder} whose
 * text can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 */
public interface MutableOptionalTextHolder extends OptionalTextHolder {
  /**
   * Removes the text of the current {@link MutableOptionalTextHolder}.
   */
  void removeText();

  /**
   * Sets the text of the current {@link MutableOptionalTextHolder}.
   * 
   * @param text
   * @throws RuntimeException if the given text is null
   */
  void setText(String text);
}
