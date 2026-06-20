/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mutablemandatoryattribute;

import ch.nolix.baseapi.attribute.mandatoryattribute.ContentHolder;

/**
 * A {@link MutableContentHolder} is a {@link ContentHolder} whose content can
 * be set programmatically.
 * 
 * @author Silvan Wyss
 * @param <C> the type of the content of a {@link MutableContentHolder}
 */
public interface MutableContentHolder<C> extends ContentHolder<C> {
  /**
   * Sets the content of the current {@link MutableContentHolder}.
   * 
   * @param content
   * @throws RuntimeException if the given content is null
   */
  void setContent(C content);
}
