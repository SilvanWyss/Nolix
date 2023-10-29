//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//own imports
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IMultiValueEntry<

V>
extends IDatabaseObject {

  //method declaration
  IMultiValue<V> getStoredParentMultiValue();

  //method declaration
  V getStoredValue();
}
