//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IMultiValueEntry<

V>
extends IDatabaseObject {

  //method declaration
  IMultiValue<V> getStoredParentMultiValue();

  //method declaration
  V getStoredValue();
}
