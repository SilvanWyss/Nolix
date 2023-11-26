//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//own imports
import ch.nolix.coreapi.methodapi.mutationapi.Clearable;

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
