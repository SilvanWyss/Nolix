//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

import ch.nolix.coreapi.functionapi.mutationapi.Clearable;

//interface
public interface IOptionalReference<

E extends IEntity>
extends Clearable, IBaseReference<E> {

  //method declaration
  E getReferencedEntity();

  //method declaration
  String getReferencedEntityId();

  //method declaration
  void setEntity(E entity);

  //method declaration
  void setEntityById(String id);
}
