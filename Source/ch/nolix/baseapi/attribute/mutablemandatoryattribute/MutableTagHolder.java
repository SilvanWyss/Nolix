/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.TagHolder;

/**
 * A {@link MutableTagHolder} is a {@link TagHolder} whose tag can be set
 * programmatically.
 * 
 * @author Silvan Wyss
 */
public interface MutableTagHolder extends TagHolder {
  /**
   * Sets the tag of the current {@link MutableTagHolder}.
   * 
   * @param tag
   * @throws RuntimeException if the given tag is null or blank
   */
  void setTag(String tag);
}
