//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.functionapi.mutationapi.Clearable;

//interface
public interface IMultiReference<

    E extends IEntity>
    extends Clearable, IBaseReference<E> {

  //method declaration
  void addEntity(Object entity);

  //method declaration
  IContainer<E> getReferencedEntities();

  //method declaration
  IContainer<String> getReferencedEntityIds();

  //method declaration
  IContainer<? extends IMultiReferenceEntry<E>> getStoredLocalEntries();

  //method declaration
  void removeEntity(E entity);
}
