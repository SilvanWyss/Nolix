package ch.nolix.core.container.pair;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.commontypetoolapi.stringtoolapi.IStringTool;
import ch.nolix.coreapi.containerapi.pairapi.IKeyValuePair;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

public final class KeyValuePair<K, V> implements IKeyValuePair<K, V> {

  private static final IStringTool STRING_TOOL = new StringTool();

  private final K key;

  private final V value;

  //For a better performance, this implementation does not use all comfortable methods.
  private KeyValuePair(final K key, final V value) {

    if (key == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.KEY);
    }

    if (value == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.VALUE);
    }

    this.key = key;
    this.value = value;
  }

  public static <LK, LV> KeyValuePair<LK, LV> withKeyAndValue(final LK key, final LV value) {
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

  //For a better performance, this implementation does not use all comfortable methods.
  @Override
  public String toString() {
    return STRING_TOOL.getInParentheses(key + ";" + value);
  }
}
