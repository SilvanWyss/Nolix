package ch.nolix.system.objectdata.datatool;

import ch.nolix.core.reflection.GlobalReflectionTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.objectdataapi.datatoolapi.IEntityCreator;

/**
 * @author Silvan Wyss
 * @version 2022-03-04
 */
public final class EntityCreator implements IEntityCreator {

  /**
   * {@inheritDoc}
   */
  @Override
  public <E extends IEntity> E createEmptyEntityForTable(final ITable<E> table) {

    final var entityType = table.getEntityType();

    return createEmptyEntityForEntityType(entityType);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <E extends IEntity> E createEmptyEntityForEntityType(final Class<E> entityType) {
    return GlobalReflectionTool.createInstanceFromDefaultConstructorOfClass(entityType);
  }
}
