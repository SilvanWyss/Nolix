/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.entitytool;

import ch.nolix.systemapi.objectdata.model.IEntity;

/**
 * @author Silvan Wyss
 */
public interface IEntityCreator {
  /**
   * @param entityType
   * @param <E>        is the type of the created {@link IEntity}.
   * @return a new empty {@link IEntity} of the given entityType.
   * @throws RuntimeException if the given entityType is null.
   */
  <E extends IEntity> E createEmptyEntityForEntityType(Class<E> entityType);
}
