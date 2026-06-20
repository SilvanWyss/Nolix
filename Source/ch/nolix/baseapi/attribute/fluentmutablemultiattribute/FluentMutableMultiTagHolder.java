/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutablemultiattribute;

import ch.nolix.baseapi.attribute.multiattribute.MultiTagHolder;

/**
 * A {@link FluentMutableMultiTagHolder} is a {@link MultiTagHolder} whose
 * tags can be added programmatically and fluently and removed programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableMultiTagHolder}
 */
public interface FluentMutableMultiTagHolder<H extends FluentMutableMultiTagHolder<H>> extends MultiTagHolder {
  /**
   * Adds the given tag to the current {@link FluentMutableMultiTagHolder} if the
   * current {@link FluentMutableMultiTagHolder} does not contain already the
   * given tag.
   * 
   * @param tag
   * @return the current {@link FluentMutableMultiTagHolder}
   * @throws RuntimeException if the given tag is null or blank
   */
  H addTag(String tag);

  /**
   * Removes the given tag from the current {@link FluentMutableMultiTagHolder}
   * if the current {@link FluentMutableMultiTagHolder} contains the given tag.
   * 
   * @param tag
   */
  void removeTag(String tag);

  /**
   * Removes all tags from the current {@link FluentMutableMultiTagHolder}.
   */
  void removeTags();
}
