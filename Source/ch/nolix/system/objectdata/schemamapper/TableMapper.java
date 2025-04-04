package ch.nolix.system.objectdata.schemamapper;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.model.Table;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntityTypeSet;
import ch.nolix.systemapi.objectdataapi.schemamapperapi.ITableMapper;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;

public final class TableMapper implements ITableMapper {

  @Override
  public ITable mapEntityTypeToEmptyTable(final Class<? extends IEntity> entityType) {

    final var name = entityType.getSimpleName();

    return Table.withName(name);
  }

  @Override
  public IContainer<ITable> mapSchemaToEmptyTables(final IEntityTypeSet entityTypeSet) {

    final var entityTypes = entityTypeSet.getEntityTypes();

    return entityTypes.to(this::mapEntityTypeToEmptyTable);
  }
}
