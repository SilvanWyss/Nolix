/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemultiattribute;

import ch.nolix.baseapi.attribute.multiattribute.MultiTextHolder;

/**
 * A {@link MutableMultiTextHolder} is a {@link MultiTextHolder} whose texts can
 * be added and removed programmatically.
 * 
 * @author Silvan Wyss
 */
public interface MutableMultiTextHolder extends MultiTextHolder {
  /**
   * Adds the given text to the current {@link MutableMultiTextHolder} if the
   * current {@link MutableMultiTextHolder} does not contain already the given
   * text.
   * 
   * @param text
   * @throws RuntimeException if the given text is null or blank
   */
  void addText(String text);

  /**
   * Removes the given text from the current {@link MutableMultiTextHolder} if the
   * current {@link MutableMultiTextHolder} contains the given text.
   * 
   * @param text
   */
  void removeText(String text);

  /**
   * Removes all texts from the current {@link MutableMultiTextHolder}.
   */
  void removeTexts();
}
