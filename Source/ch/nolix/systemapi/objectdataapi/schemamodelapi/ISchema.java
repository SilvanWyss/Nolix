package ch.nolix.systemapi.objectdataapi.schemamodelapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;

public interface ISchema {

  IContainer<Class<? extends IEntity>> getEntityTypes();
}
