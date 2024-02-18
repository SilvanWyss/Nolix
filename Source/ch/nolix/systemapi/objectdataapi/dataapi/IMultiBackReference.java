//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IMultiBackReference<E extends IEntity> extends IBaseBackReference<E> {

  //method declaration
  IContainer<String> getAllBackReferencedEntityIds();

  //method declaration
  IContainer<E> getAllStoredBackReferencedEntities();

  //method declaration
  IContainer<? extends IMultiBackReferenceEntry<E>> getStoredNewAndDeletedEntries();

  //method declaration
  boolean loadedAllPersistedReferencedEntityIds();
}
