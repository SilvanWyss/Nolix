//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//interface
public interface IBackReference<E extends IEntity> extends IBaseBackReference<E> {

  //method declaration
  String getBackReferencedEntityId();

  //method declaration
  E getStoredBackReferencedEntity();

  //method declaration
  boolean referencesBackEntity();
}
