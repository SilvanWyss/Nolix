package ch.nolix.system.objectdata.schemamapper;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.system.objectschema.model.Table;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.IEntityTypeSet;
import ch.nolix.systemapi.objectdata.schemamapper.ITableMapper;
import ch.nolix.systemapi.objectschema.model.ITable;

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
