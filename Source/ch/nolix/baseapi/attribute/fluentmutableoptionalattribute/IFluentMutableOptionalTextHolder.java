/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.ITextHolder;

/**
 * A {@link IFluentMutableOptionalTextHolder} is a {@link ITextHolder} whose
 * text can be set programmatically and fluently and removed programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link IFluentMutableOptionalTextHolder}
 */
public interface IFluentMutableOptionalTextHolder<H extends IFluentMutableOptionalTextHolder<H>> extends ITextHolder {
  /**
   * Removes the text of the current {@link IFluentMutableOptionalTextHolder}.
   */
  void removeText();

  /**
   * Sets the text of the current {@link IFluentMutableOptionalTextHolder}.
   * 
   * @param text
   * @return the current {@link IFluentMutableOptionalTextHolder}
   * @throws RuntimeException if the given text is null
   */
  H setText(String text);
}
