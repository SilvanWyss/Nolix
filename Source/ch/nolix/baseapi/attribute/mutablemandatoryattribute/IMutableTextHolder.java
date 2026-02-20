/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.ITextHolder;

/**
 * A {@link IMutableTextHolder} is a {@link ITextHolder} whose text can be set
 * programmatically.
 * 
 * @author Silvan Wyss
 */
public interface IMutableTextHolder extends ITextHolder {
  /**
   * Sets the text of the current {@link IMutableTextHolder}.
   * 
   * @param text
   * @throws RuntimeException if the given text is null.
   */
  void setText(String text);
}
