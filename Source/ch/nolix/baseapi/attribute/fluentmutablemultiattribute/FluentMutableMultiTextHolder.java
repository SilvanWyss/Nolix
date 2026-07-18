/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutablemultiattribute;

import ch.nolix.baseapi.attribute.multiattribute.MultiTextHolder;

/**
 * A {@link FluentMutableMultiTextHolder} is a {@link MultiTextHolder} whose
 * texts can be added programmatically and fluently and removed
 * programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableMultiTextHolder}
 */
public interface FluentMutableMultiTextHolder<H extends FluentMutableMultiTextHolder<H>> extends MultiTextHolder {
  /**
   * Adds the given text to the current {@link FluentMutableMultiTextHolder} if
   * the current {@link FluentMutableMultiTextHolder} does not contain already the
   * given text.
   * 
   * @param text
   * @return the current {@link FluentMutableMultiTextHolder}
   * @throws RuntimeException if the given text is null or blank
   */
  H addText(String text);

  /**
   * Removes the given text from the current {@link FluentMutableMultiTextHolder}
   * if the current {@link FluentMutableMultiTextHolder} contains the given text.
   * 
   * @param text
   */
  void removeText(String text);

  /**
   * Removes all texts from the current {@link FluentMutableMultiTextHolder}.
   */
  void removeTexts();
}
