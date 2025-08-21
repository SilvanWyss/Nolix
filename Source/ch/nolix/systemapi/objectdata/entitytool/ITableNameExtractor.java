package ch.nolix.systemapi.objectdata.entitytool;

import ch.nolix.systemapi.objectdata.model.IEntity;

public interface ITableNameExtractor {

  String getTableNameOfEntity(IEntity entity);

  String getTableNameOfEntityType(Class<? extends IEntity> entityType);
}
