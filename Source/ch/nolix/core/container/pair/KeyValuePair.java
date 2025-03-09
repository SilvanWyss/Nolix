package ch.nolix.core.container.pair;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.containerapi.pairapi.IKeyValuePair;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

public final class KeyValuePair<K, V> implements IKeyValuePair<K, V> {

  private final K key;

  private final V value;

  //For a better performance, this implementation does not use all available comfort methods.
  private KeyValuePair(final K key, final V value) {

    if (key == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalog.KEY);
    }

    if (value == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalog.VALUE);
    }

    this.key = key;
    this.value = value;
  }

  public static <K2, V2> KeyValuePair<K2, V2> withKeyAndValue(final K2 key, final V2 value) {
    return new KeyValuePair<>(key, value);
  }

  @Override
  public K getKey() {
    return key;
  }

  @Override
  public V getStoredValue() {
    return value;
  }

  //For a better performance, this implementation does not use all available comfort methods.
  @Override
  public String toString() {
    return StringTool.getInParentheses(key + ";" + value);
  }
}
