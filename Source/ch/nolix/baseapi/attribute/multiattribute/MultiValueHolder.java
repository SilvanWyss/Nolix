/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.multiattribute;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;

/**
 * A {@link MultiValueHolder} can contain several values.
 * 
 * @author Silvan Wyss
 * @param <V> the type of the values of a {@link MultiValueHolder}
 */
public interface MultiValueHolder<V> {
  /**
   * @return the values of the current {@link MultiValueHolder}
   */
  IWellOrderContainer<V> getStoredValues();
}
