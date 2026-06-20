/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.optionalattribute;

/**
 * A {@link OptionalContentHolder} can have a content.
 * 
 * @author Silvan Wyss
 * @param <C> the type of the content of a {@link OptionalContentHolder}
 */
public interface OptionalContentHolder<C> {
  /**
   * @return the content of the current {@link OptionalContentHolder}
   * @throws RuntimeException if the current {@link OptionalContentHolder} does
   *                          not have a content
   */
  C getStoredContent();

  /**
   * @return true if the current {@link OptionalContentHolder} has a content,
   *         false otherwise
   */
  boolean hasContent();
}
