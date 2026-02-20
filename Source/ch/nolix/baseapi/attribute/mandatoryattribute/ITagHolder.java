/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mandatoryattribute;

/**
 * A {@link ITagHolder} has a tag.
 * 
 * @author Silvan Wyss
 */
public interface ITagHolder {
  /**
   * @return the tag of the current {@link ITagHolder}.
   */
  String getTag();
}
