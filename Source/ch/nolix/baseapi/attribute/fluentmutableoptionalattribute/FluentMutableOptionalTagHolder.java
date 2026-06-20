/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutableoptionalattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.TagHolder;

/**
 * A {@link FluentMutableOptionalTagHolder} is a {@link TagHolder} whose tag
 * can be set programmatically and fluently and removed programmatically.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableOptionalTagHolder}
 */
public interface FluentMutableOptionalTagHolder<H extends FluentMutableOptionalTagHolder<H>> extends TagHolder {
  /**
   * Removes the tag of the current {@link FluentMutableOptionalTagHolder}.
   */
  void removeTag();

  /**
   * Sets the tag of the current {@link FluentMutableOptionalTagHolder}.
   * 
   * @param tag
   * @return the current {@link FluentMutableOptionalTagHolder}
   * @throws RuntimeException if the given tag is null or blank
   */
  H setTag(String tag);
}
