//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface IMultiBackReference<E extends IEntity> extends IBaseBackReference<E> {

  //method declaration
  IContainer<E> getStoredBackReferencedEntities();

  //method declaration
  IContainer<String> getBackReferencedEntityIds();
}
