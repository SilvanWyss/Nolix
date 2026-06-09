/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutablemultiattribute;

import ch.nolix.baseapi.attribute.multiattribute.IMultiTextHolder;

/**
 * A {@link IFluentMutableMultiTextHolder} is a {@link IMultiTextHolder} whose
 * texts can be added programmatically and fluently and removed
 * programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link IFluentMutableMultiTextHolder}
 */
public interface IFluentMutableMultiTextHolder<H extends IFluentMutableMultiTextHolder<H>> extends IMultiTextHolder {
  /**
   * Adds the given text to the current {@link IFluentMutableMultiTextHolder} if
   * the current {@link IFluentMutableMultiTextHolder} does not contain already
   * the given text.
   * 
   * @param text
   * @return the current {@link IFluentMutableMultiTextHolder}
   * @throws RuntimeException if the given text is null or blank
   */
  H addText(String text);

  /**
   * Removes the given text from the current {@link IFluentMutableMultiTextHolder}
   * if the current {@link IFluentMutableMultiTextHolder} contains the given text.
   * 
   * @param text
   */
  void removeText(String text);

  /**
   * Removes all texts from the current {@link IFluentMutableMultiTextHolder}.
   */
  void removeTexts();
}
