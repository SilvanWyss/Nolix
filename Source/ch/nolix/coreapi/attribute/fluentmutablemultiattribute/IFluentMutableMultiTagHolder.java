/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.attribute.fluentmutablemultiattribute;

import ch.nolix.coreapi.attribute.multiattribute.IMultiTagHolder;

/**
 * A {@link IFluentMutableMultiTagHolder} is a {@link IMultiTagHolder} whose
 * tags can be added and removed programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> is the type of a {@link IFluentMutableMultiTagHolder}.
 */
public interface IFluentMutableMultiTagHolder<H extends IFluentMutableMultiTagHolder<H>> extends IMultiTagHolder {
  /**
   * Adds the given tag to the current {@link IFluentMutableMultiTagHolder} if the
   * current {@link IFluentMutableMultiTagHolder} does not contain already such a
   * tag.
   * 
   * @param tag
   * @return the current {@link IFluentMutableMultiTagHolder}.
   * @throws RuntimeException if the given tag is null or blank.
   */
  H addTag(String tag);

  /**
   * Removes the tag that equals the given tag from the current
   * {@link IFluentMutableMultiTagHolder} if the current
   * {@link IFluentMutableMultiTagHolder} contains such a tag.
   * 
   * @param tag
   */
  void removeTag(String tag);

  /**
   * Removes all tags from the current {@link IFluentMutableMultiTagHolder}.
   */
  void removeTags();
}
