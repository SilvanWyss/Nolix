/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemultiattribute;

import ch.nolix.baseapi.attribute.multiattribute.MultiTagHolder;

/**
 * A {@link MutableMultiTagHolder} is a {@link MultiTagHolder} whose tags can be
 * added and removed programmatically.
 * 
 * @author Silvan Wyss
 */
public interface MutableMultiTagHolder extends MultiTagHolder {
  /**
   * Adds the given tag to the current {@link MutableMultiTagHolder} if the
   * current {@link MutableMultiTagHolder} does not contain already the given tag.
   * 
   * @param tag
   * @throws RuntimeException if the given tag is null or blank
   */
  void addTag(String tag);

  /**
   * Removes the given tag from the current {@link MutableMultiTagHolder} if the
   * current {@link MutableMultiTagHolder} contains the given a tag.
   * 
   * @param tag
   */
  void removeTag(String tag);

  /**
   * Removes all tags from the current {@link MutableMultiTagHolder}.
   */
  void removeTags();
}
