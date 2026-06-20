/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutableoptionalattribute;

import ch.nolix.baseapi.attribute.optionalattribute.OptionalContentHolder;

/**
 * A {@link MutableOptionalContentHolder} is a {@link OptionalContentHolder}
 * whose content can be set and removed programmatically.
 * 
 * @author Silvan Wyss
 * @param <C> the type of the content of a {@link MutableOptionalContentHolder}
 */
public interface MutableOptionalContentHolder<C> extends OptionalContentHolder<C> {
  /**
   * Removes the content of the current {@link MutableOptionalContentHolder}.
   */
  void removeContent();

  /**
   * Sets the content of the current {@link MutableOptionalContentHolder}.
   * 
   * @param content
   * @throws RuntimeException if the given content is null
   */
  void setContent(C content);
}
