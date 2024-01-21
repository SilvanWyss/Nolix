//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

import ch.nolix.coreapi.functionapi.mutationapi.Clearable;

//interface
public interface IOptionalValue<V> extends Clearable, IBaseValue<V> {

  //method declaration
  V getStoredValue();

  //method declaration
  void setValue(V value);

  //method declaration
  void setValueFromString(String string);
}
