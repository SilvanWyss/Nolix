/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.OptionalContentHolder;

/**
 * A {@link FluentMutableOptionalContentHolder} is a
 * {@link OptionalContentHolder} whose content can be set programmatically and
 * fluently and removed programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableOptionalContentHolder}
 * @param <C> the type of the content of a
 *            {@link FluentMutableOptionalContentHolder}
 */
public interface FluentMutableOptionalContentHolder<H extends FluentMutableOptionalContentHolder<H, C>, C>
extends OptionalContentHolder<C> {
  /**
   * Removes the content of the current
   * {@link FluentMutableOptionalContentHolder}.
   */
  void removeContent();

  /**
   * Sets the content of the current {@link FluentMutableOptionalContentHolder}.
   * 
   * @param content
   * @return the current {@link FluentMutableOptionalContentHolder}
   * @throws RuntimeException if the given content is null
   */
  H setContent(String content);
}
