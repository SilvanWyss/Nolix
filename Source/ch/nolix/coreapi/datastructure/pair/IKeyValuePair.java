package ch.nolix.coreapi.datastructure.pair;

/**
 * @author Silvan Wyss
 * @param <K> is the type of the key of a {@link IKeyValuePair}.
 * @param <V> is the type of the value of a {@link IKeyValuePair}.
 */
public interface IKeyValuePair<K, V> {
  K getKey();

  V getStoredValue();
}
