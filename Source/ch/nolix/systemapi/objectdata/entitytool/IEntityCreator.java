/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.objectdata.entitytool;

import ch.nolix.systemapi.objectdata.model.Entity;

/**
 * @author Silvan Wyss
 */
public interface IEntityCreator {
  /**
   * @param entityType
   * @param <E>        the type of the created {@link Entity}
   * @return a new empty {@link Entity} of the given entityType
   * @throws RuntimeException if the given entityType is null
   */
  <E extends Entity> E createEmptyEntityForEntityType(Class<E> entityType);
}
