/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.optionalattribute;

/**
 * A {@link OptionalTextHolder} can have a text.
 * 
 * @author Silvan Wyss
 */
public interface OptionalTextHolder {
  /**
   * @return the text of the current {@link OptionalTextHolder}
   * @throws RuntimeException if the current {@link OptionalTextHolder} does not
   *                          have a text
   */
  String getText();

  /**
   * @return true if the current {@link OptionalTextHolder} has a text, false
   *         otherwise
   */
  boolean hasText();
}
