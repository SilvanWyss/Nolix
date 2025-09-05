package ch.nolix.system.objectdata.entitytool;

import ch.nolix.core.reflection.reflectiontool.ReflectionTool;
import ch.nolix.systemapi.objectdata.entitytool.IEntityCreator;
import ch.nolix.systemapi.objectdata.model.IEntity;

/**
 * @author Silvan Wyss
 * @version 2022-03-04
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
