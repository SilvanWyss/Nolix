package ch.nolix.system.objectdata.entitytool;

import ch.nolix.systemapi.objectdata.entitytool.ITableNameExtractor;
import ch.nolix.systemapi.objectdata.model.IEntity;

public final class TableNameExtractor implements ITableNameExtractor {
  @Override
  public String getTableNameOfEntity(final IEntity entity) {
    final var entityType = entity.getClass();

    return getTableNameOfEntityType(entityType);
  }

  @Override
  public String getTableNameOfEntityType(final Class<? extends IEntity> entityType) {
    return entityType.getSimpleName();
  }
}
