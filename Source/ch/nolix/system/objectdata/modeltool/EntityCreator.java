package ch.nolix.system.objectdata.modeltool;

import ch.nolix.core.reflection.reflectiontool.ReflectionTool;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modeltool.IEntityCreator;

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

  /**
   * {@inheritDoc}
   */
  @Override
  public <E extends IEntity> E createEmptyEntityForTable(final ITable<E> table) {

    final var entityType = table.getEntityType();

    return createEmptyEntityForEntityType(entityType);
  }
}
