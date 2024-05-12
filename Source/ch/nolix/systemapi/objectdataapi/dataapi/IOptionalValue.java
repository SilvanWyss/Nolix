//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

import ch.nolix.coreapi.stateapi.statemutationapi.Clearable;

//interface
public interface IOptionalValue<V> extends Clearable, IBaseValue<V> {

  //method declaration
  V getStoredValue();

  //method declaration
  void setValue(V value);

  //method declaration
  void setValueFromString(String string);
}
