/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mandatoryattribute;

/**
 * A {@link ContentHolder} has a content.
 * 
 * @author Silvan Wyss
 * @param <C> the type of the content of a {@link ContentHolder}
 */
public interface ContentHolder<C> {
  /**
   * @return the content of the current {@link ContentHolder}
   */
  C getStoredContent();
}
