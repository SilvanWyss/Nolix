//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//interface
public interface IReference<E extends IEntity> extends IBaseReference<E> {

  //method declaration
  String getReferencedEntityId();

  //method declaration
  E getStoredReferencedEntity();

  //method declaration
  void setEntity(Object entity);

  //method declaration
  void setEntityById(String id);
}
