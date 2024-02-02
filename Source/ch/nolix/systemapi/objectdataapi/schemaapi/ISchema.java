//package declaration
package ch.nolix.systemapi.objectdataapi.schemaapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;

//interface
public interface ISchema {

  //method declaration
  Class<? extends IEntity> getEntityTypeByName(String name);

  //method declaration
  IContainer<Class<? extends IEntity>> getEntityTypes();
}
