//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//own imports
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IMultiBackReferenceEntry<E extends IEntity> extends IDatabaseObject {

  //method declaration
  String getBackReferencedEntityId();

  //method declaration
  E getStoredBackReferencedEntity();

  //method declaration
  IMultiBackReference<E> getStoredParentMultiBackReference();
}
