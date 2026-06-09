/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.ITagHolder;

/**
 * A {@link IFluentMutableOptionalTagHolder} is a {@link ITagHolder} whose tag
 * can be set programmatically and fluently and removed programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link IFluentMutableOptionalTagHolder}
 */
public interface IFluentMutableOptionalTagHolder<H extends IFluentMutableOptionalTagHolder<H>> extends ITagHolder {
  /**
   * Removes the tag of the current {@link IFluentMutableOptionalTagHolder}.
   */
  void removeTag();

  /**
   * Sets the tag of the current {@link IFluentMutableOptionalTagHolder}.
   * 
   * @param tag
   * @return the current {@link IFluentMutableOptionalTagHolder}
   * @throws RuntimeException if the given tag is null or blank
   */
  H setTag(String tag);
}
