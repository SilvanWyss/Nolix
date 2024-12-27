package ch.nolix.systemapi.objectdataapi.schemaapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;

public interface ISchema {

  Class<? extends IEntity> getEntityTypeByName(String name);

  IContainer<Class<? extends IEntity>> getEntityTypes();
}
