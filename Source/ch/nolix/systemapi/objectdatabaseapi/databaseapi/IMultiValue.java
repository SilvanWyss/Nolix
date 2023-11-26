//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.methodapi.mutationapi.Clearable;

//interface
public interface IMultiValue<

V>
extends Clearable, IBaseValue<V> {

  //method declaration
  void addValue(V value);

  //method declaration
  IContainer<? extends IMultiValueEntry<V>> getStoredLocalEntries();

  //method declaration
  IContainer<V> getStoredValues();

  //method declaration
  void removeValue(V value);
}
