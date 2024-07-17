//package declaration
package ch.nolix.core.container.pair;

import ch.nolix.core.commontypetool.stringtool.GlobalStringTool;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.containerapi.pairapi.IKeyValuePair;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public final class KeyValuePair<K, V> implements IKeyValuePair<K, V> {

  //attribute
  private final K key;

  //attribute
  private final V value;

  //constructor
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

  //static method
  public <LK, LV> KeyValuePair<LK, LV> withKeyAndValue(final LK key, final LV value) {
    return new KeyValuePair<>(key, value);
  }

  //method
  @Override
  public K getKey() {
    return key;
  }

  //method
  @Override
  public V getStoredValue() {
    return value;
  }

  //method
  //For a better performance, this implementation does not use all comfortable methods.
  @Override
  public String toString() {
    return GlobalStringTool.getInParantheses(key + ";" + value);
  }
}
