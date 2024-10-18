package ch.nolix.system.objectdata.data;

import ch.nolix.core.reflection.GlobalReflectionTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

public final class EntityCreator {

  public <E extends IEntity> E createEmptyEntityForTable(final ITable<E> table) {

    final var entityType = table.getEntityType();

    return createEmptyEntityForEntityType(entityType);
  }

  public <E extends IEntity> E createEmptyEntityForEntityType(final Class<E> entityType) {
    return GlobalReflectionTool.createInstanceFromDefaultConstructorOfClass(entityType);
  }
}
