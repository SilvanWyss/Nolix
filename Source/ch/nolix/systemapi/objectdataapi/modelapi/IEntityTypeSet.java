package ch.nolix.systemapi.objectdataapi.modelapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface IEntityTypeSet {

  IContainer<Class<? extends IEntity>> getEntityTypes();
}
