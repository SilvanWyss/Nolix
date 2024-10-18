package ch.nolix.coreapi.attributeapi.mutablemultiattributeapi;

import ch.nolix.coreapi.attributeapi.multiattributeapi.IMultiTextHolder;

/**
 * A {@link IMutableMultiTextHolder} is a {@link IMultiTextHolder} whose texts
 * can be added and removed programmatically.
 * 
 * @author Silvan Wyss
 * @version 2023-10-25
 */
public interface IMutableMultiTextHolder extends IMultiTextHolder {

  /**
   * Adds the given text to the current {@link IMutableMultiTextHolder}.
   * 
   * @param text
   * @throws RuntimeException if the given text is null.
   */
  void addText(String text);

  /**
   * Removes the given text from the current {@link IMutableMultiTextHolder}.
   * 
   * @param text
   * @throws RuntimeException if the current {@link IMutableMultiTextHolder} does
   *                          not contain the given text.
   */
  void removeText(String text);

  /**
   * Removes all texts from the current {@link IMutableMultiTextHolder}.
   */
  void removeTexts();
}
