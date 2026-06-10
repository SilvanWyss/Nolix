/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemultiattribute;

import ch.nolix.baseapi.attribute.multiattribute.IMultiTextHolder;

/**
 * A {@link IMutableMultiTextHolder} is a {@link IMultiTextHolder} whose texts
 * can be added and removed programmatically.
 * 
 * @author Silvan Wyss
 */
public interface IMutableMultiTextHolder extends IMultiTextHolder {
  /**
   * Adds the given text to the current {@link IMutableMultiTextHolder} if the
   * current {@link IMutableMultiTextHolder} does not contain already the given
   * text.
   * 
   * @param text
   * @throws RuntimeException if the given text is null or blank
   */
  void addText(String text);

  /**
   * Removes the given text from the current {@link IMutableMultiTextHolder} if
   * the current {@link IMutableMultiTextHolder} contains the given text.
   * 
   * @param text
   */
  void removeText(String text);

  /**
   * Removes all texts from the current {@link IMutableMultiTextHolder}.
   */
  void removeTexts();
}
