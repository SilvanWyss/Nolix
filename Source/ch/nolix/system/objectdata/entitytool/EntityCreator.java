/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.entitytool;

import ch.nolix.base.reflection.reflectiontool.ReflectionTool;
import ch.nolix.systemapi.objectdata.entitytool.IEntityCreator;
import ch.nolix.systemapi.objectdata.model.Entity;

/**
 * @author Silvan Wyss
 */
public final class EntityCreator implements IEntityCreator {
  /**
   * {@inheritDoc}
   */
  @Override
  public <E extends Entity> E createEmptyEntityForEntityType(final Class<E> entityType) {
    return ReflectionTool.createInstanceFromDefaultConstructorOfClass(entityType);
  }
}
