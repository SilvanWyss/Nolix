/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.TagHolder;

/**
 * A {@link FluentMutableTagHolder} is a {@link TagHolder} whose tag can be set
 * programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableTagHolder}
 */
public interface FluentMutableTagHolder<H extends FluentMutableTagHolder<H>> extends TagHolder {
  /**
   * Sets the tag of the current {@link FluentMutableTagHolder}.
   * 
   * @param tag
   * @return the current {@link FluentMutableTagHolder}
   * @throws RuntimeException if the given tag is null or blank
   */
  H setTag(String tag);
}
