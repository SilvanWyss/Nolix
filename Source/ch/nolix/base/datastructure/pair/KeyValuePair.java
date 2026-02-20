/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.datastructure.pair;

import ch.nolix.base.commontypetool.stringtool.StringTool;
import ch.nolix.base.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.datastructure.pair.IKeyValuePair;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @param <K> is the type of the key of of a {@link KeyValuePair}.
 * @param <V> is the type of the value of of a {@link KeyValuePair}.
 */
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

  /**
   * {@inheritDoc}
   */
  @Override
  public K getKey() {
    return key;
  }

  /**
   * {@inheritDoc}
   */
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
