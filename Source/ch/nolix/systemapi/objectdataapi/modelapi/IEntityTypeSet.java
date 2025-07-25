package ch.nolix.systemapi.objectdataapi.modelapi;

import ch.nolix.coreapi.container.base.IContainer;

public interface IEntityTypeSet {

  IContainer<Class<? extends IEntity>> getEntityTypes();
}
