/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.optionalattribute;

/**
 * A {@link OptionalTagHolder} can have a tag.
 * 
 * @author Silvan Wyss
 */
public interface OptionalTagHolder {
  /**
   * @return the tag of the current {@link OptionalTagHolder}
   * @throws RuntimeException if the current {@link OptionalTagHolder} does not
   *                          have a tag
   */
  String getTag();

  /**
   * @return true if the current {@link OptionalTagHolder} has a tag, false
   *         otherwise, false otherwise
   */
  boolean hasTag();
}
