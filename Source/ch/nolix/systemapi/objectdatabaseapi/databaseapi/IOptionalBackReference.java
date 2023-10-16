//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//interface
public interface IOptionalBackReference<

    E extends IEntity>
    extends IBaseBackReference<E> {

  //method declaration
  E getBackReferencedEntity();

  //method declaration
  String getBackReferencedEntityId();
}
