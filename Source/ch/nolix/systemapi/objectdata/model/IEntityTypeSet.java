package ch.nolix.systemapi.objectdata.model;

import ch.nolix.coreapi.container.base.IContainer;

public interface IEntityTypeSet {
  IContainer<Class<? extends IEntity>> getEntityTypes();
}
