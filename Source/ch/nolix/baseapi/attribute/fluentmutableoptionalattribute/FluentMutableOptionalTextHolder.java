/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.TextHolder;

/**
 * A {@link FluentMutableOptionalTextHolder} is a {@link TextHolder} whose text
 * can be set programmatically and fluently and removed programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableOptionalTextHolder}
 */
public interface FluentMutableOptionalTextHolder<H extends FluentMutableOptionalTextHolder<H>> extends TextHolder {
  /**
   * Removes the text of the current {@link FluentMutableOptionalTextHolder}.
   */
  void removeText();

  /**
   * Sets the text of the current {@link FluentMutableOptionalTextHolder}.
   * 
   * @param text
   * @return the current {@link FluentMutableOptionalTextHolder}
   * @throws RuntimeException if the given text is null
   */
  H setText(String text);
}
