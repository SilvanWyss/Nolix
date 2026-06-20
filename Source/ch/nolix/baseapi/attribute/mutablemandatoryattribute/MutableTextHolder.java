/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.TextHolder;

/**
 * A {@link MutableTextHolder} is a {@link TextHolder} whose text can be set
 * programmatically.
 * 
 * @author Silvan Wyss
 */
public interface MutableTextHolder extends TextHolder {
  /**
   * Sets the text of the current {@link MutableTextHolder}.
   * 
   * @param text
   * @throws RuntimeException if the given text is null
   */
  void setText(String text);
}
