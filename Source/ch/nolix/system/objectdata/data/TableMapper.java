package ch.nolix.system.objectdata.data;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadto.TableDto;

final class TableMapper {

  private static final ColumnMapper COLUMN_MAPPER = new ColumnMapper();

  @SuppressWarnings("unchecked")
  public Table<IEntity> createEmptyTableFromTableDtoForDatabase(
    final TableDto tableDto,
    final Database database) {
    return Table.withParentDatabaseAndNameAndIdAndEntityClassAndColumns(
      database,
      tableDto.name(),
      tableDto.id(),
      (Class<IEntity>) database.internalGetSchema().getEntityTypeByName(tableDto.name()));
  }

  public ITable<IEntity> createTableFromTableDtoForDatabaseUsingGivenReferencableTables(
    final TableDto tableDto,
    final Database database,
    final IContainer<ITable<IEntity>> referencableTables) {

    final var table = createEmptyTableFromTableDtoForDatabase(tableDto, database);

    final var columns = tableDto.columns()
      .to(
        c -> COLUMN_MAPPER.createColumnFromDtoForParentTableUsingGivenReferencableTables(c, table,
          referencableTables));

    table.internalSetColumns(columns);

    return table;
  }
}
