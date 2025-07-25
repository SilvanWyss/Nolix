package ch.nolix.coreapi.container.pair;

public interface IKeyValuePair<K, V> {

  K getKey();

  V getStoredValue();
}
