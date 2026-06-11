/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.optionalattribute;

/**
 * A {@link IOptionalTagHolder} can have a tag.
 * 
 * @author Silvan Wyss
 */
public interface IOptionalTagHolder {
  /**
   * @return the tag of the current {@link IOptionalTagHolder}
   * @throws RuntimeException if the current {@link IOptionalTagHolder} does not
   *                          have a tag
   */
  String getTag();

  /**
   * @return true if the current {@link IOptionalTagHolder} has a tag, false
   *         otherwise, false otherwise
   */
  boolean hasTag();
}
