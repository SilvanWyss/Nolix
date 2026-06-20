/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.fluentmutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.ContentHolder;

/**
 * A {@link FluentMutableContentHolder} is a {@link ContentHolder} whose content
 * can be set programmatically and fluently.
 * 
 * @author Silvan Wyss
 * @param <H> the type of a {@link FluentMutableContentHolder}
 * @param <V> the type of the content of a {@link FluentMutableContentHolder}
 */
public interface FluentMutableContentHolder<H extends FluentMutableContentHolder<H, V>, V> extends ContentHolder<V> {
  /**
   * Sets the content of the current {@link FluentMutableContentHolder}.
   * 
   * @param content
   * @return the current {@link FluentMutableContentHolder}
   * @throws RuntimeException if the given content is null
   */
  H setContent(V content);
}
