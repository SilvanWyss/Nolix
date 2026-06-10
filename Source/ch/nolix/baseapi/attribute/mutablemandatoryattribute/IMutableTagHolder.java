/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.ITagHolder;

/**
 * A {@link IMutableTagHolder} is a {@link ITagHolder} whose tag can be set
 * programmatically.
 * 
 * @author Silvan Wyss
 */
public interface IMutableTagHolder extends ITagHolder {
  /**
   * Sets the tag of the current {@link IMutableTagHolder}.
   * 
   * @param tag
   * @throws RuntimeException if the given tag is null or blank
   */
  void setTag(String tag);
}
