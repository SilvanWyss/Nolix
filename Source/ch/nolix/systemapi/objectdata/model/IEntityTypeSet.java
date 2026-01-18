/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.model;

import ch.nolix.coreapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 */
public interface IEntityTypeSet {
  IContainer<Class<? extends IEntity>> getEntityTypes();
}
