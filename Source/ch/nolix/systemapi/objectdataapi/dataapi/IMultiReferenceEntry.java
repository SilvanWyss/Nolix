//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//Java imports
import java.util.Optional;

//own imports
import ch.nolix.systemapi.databaseobjectapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IMultiReferenceEntry<E extends IEntity> extends IDatabaseObject {

  //method declaration
  Optional<? extends IProperty> getOptionalStoredBackReferencingProperty();

  //method declaration
  E getReferencedEntity();

  //method declaration
  String getReferencedEntityId();

  //method declaration
  IMultiReference<E> getStoredParentMultiReference();
}