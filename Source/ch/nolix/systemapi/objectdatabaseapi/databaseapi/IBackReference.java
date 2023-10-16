//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//interface
public interface IBackReference<

    E extends IEntity>
    extends IBaseBackReference<E> {

  //method declaration
  E getBackReferencedEntity();

  //method declaration
  String getBackReferencedEntityId();

  //method declaration
  boolean referencesBackEntity();
}
