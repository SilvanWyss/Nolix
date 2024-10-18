package ch.nolix.coreapi.containerapi.pairapi;

public interface IKeyValuePair<K, V> {

  K getKey();

  V getStoredValue();
}
