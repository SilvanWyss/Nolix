//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//interface
public interface IReference<

E extends IEntity>
extends IBaseReference<E> {

  //method declaration
  E getReferencedEntity();

  //method declaration
  String getReferencedEntityId();

  //method declaration
  void setEntity(final E entity);

  //method declaration
  void setEntityById(final String id);
}
