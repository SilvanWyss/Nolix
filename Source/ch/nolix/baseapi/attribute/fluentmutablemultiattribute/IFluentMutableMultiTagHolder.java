/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutablemultiattribute;

import ch.nolix.baseapi.attribute.multiattribute.IMultiTagHolder;

/**
 * A {@link IFluentMutableMultiTagHolder} is a {@link IMultiTagHolder} whose
 * tags can be added programmatically and fluently and removed programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link IFluentMutableMultiTagHolder}
 */
public interface IFluentMutableMultiTagHolder<H extends IFluentMutableMultiTagHolder<H>> extends IMultiTagHolder {
  /**
   * Adds the given tag to the current {@link IFluentMutableMultiTagHolder} if the
   * current {@link IFluentMutableMultiTagHolder} does not contain already the
   * given tag.
   * 
   * @param tag
   * @return the current {@link IFluentMutableMultiTagHolder}
   * @throws RuntimeException if the given tag is null or blank
   */
  H addTag(String tag);

  /**
   * Removes the given tag from the current {@link IFluentMutableMultiTagHolder}
   * if the current {@link IFluentMutableMultiTagHolder} contains the given tag.
   * 
   * @param tag
   */
  void removeTag(String tag);

  /**
   * Removes all tags from the current {@link IFluentMutableMultiTagHolder}.
   */
  void removeTags();
}
