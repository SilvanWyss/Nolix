package ch.nolix.coreapi.datastructure.pair;

/**
 * @author Silvan Wyss
 */
public interface IKeyValuePair<K, V> {
  K getKey();

  V getStoredValue();
}
