package ch.nolix.systemapi.objectdata.schemamapper;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IEntityTypeSet;
import ch.nolix.systemapi.objectschema.model.ITable;

public interface ITableMapper {

  ITable mapEntityTypeToEmptyTable(Class<? extends IEntity> entityType);

  IContainer<ITable> mapSchemaToEmptyTables(IEntityTypeSet entityTypeSet);
}
