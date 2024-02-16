//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

import ch.nolix.coreapi.generalstateapi.statemutationapi.Clearable;

//interface
public interface IOptionalReference<E extends IEntity> extends Clearable, IBaseReference<E> {

  //method declaration
  E getReferencedEntity();

  //method declaration
  String getReferencedEntityId();

  //method declaration
  void setEntity(Object entity);

  //method declaration
  void setEntityById(String id);
}
