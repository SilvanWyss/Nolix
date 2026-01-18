/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.entitytool;

import ch.nolix.core.reflection.reflectiontool.ReflectionTool;
import ch.nolix.systemapi.objectdata.entitytool.IEntityCreator;
import ch.nolix.systemapi.objectdata.model.IEntity;

/**
 * @author Silvan Wyss
 */
public final class EntityCreator implements IEntityCreator {
  /**
   * {@inheritDoc}
   */
  @Override
  public <E extends IEntity> E createEmptyEntityForEntityType(final Class<E> entityType) {
    return ReflectionTool.createInstanceFromDefaultConstructorOfClass(entityType);
  }
}
