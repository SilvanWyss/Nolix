/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.datastructure.pair;

/**
 * @author Silvan Wyss
 * @param <K> the type of the key of a {@link IKeyValuePair}
 * @param <V> the type of the value of a {@link IKeyValuePair}
 */
public interface IKeyValuePair<K, V> {
  /**
   * @return the key of the current {@link IKeyValuePair}
   */
  K getKey();

  /**
   * @return the value of the current {@link IKeyValuePair}
   */
  V getStoredValue();
}
