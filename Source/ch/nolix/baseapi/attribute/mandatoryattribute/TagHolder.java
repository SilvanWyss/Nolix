/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mandatoryattribute;

/**
 * A {@link TagHolder} has a tag.
 * 
 * @author Silvan Wyss
 */
public interface TagHolder {
  /**
   * @return the tag of the current {@link TagHolder}
   */
  String getTag();
}
