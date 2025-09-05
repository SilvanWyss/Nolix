package ch.nolix.coreapi.datastructure.pair;

public interface IKeyValuePair<K, V> {
  K getKey();

  V getStoredValue();
}
