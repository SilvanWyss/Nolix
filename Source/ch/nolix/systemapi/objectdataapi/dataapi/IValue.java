//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//interface
public interface IValue<V> extends IBaseValue<V> {

  //method declaration
  V getStoredValue();

  //method declaration
  void setValue(V value);

  //method declaration
  void setValueFromString(String string);
}