/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.TextHolder;

/**
 * A {@link FluentMutableTextHolder} is a {@link TextHolder} whose text can be
 * set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableTextHolder}
 */
public interface FluentMutableTextHolder<H extends FluentMutableTextHolder<H>> extends TextHolder {
  /**
   * Sets the text of the current {@link FluentMutableTextHolder}.
   * 
   * @param text
   * @return the current {@link FluentMutableTextHolder}
   * @throws RuntimeException if the given text is null
   */
  H setText(String text);
}
