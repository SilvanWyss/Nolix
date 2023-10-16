//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;

//class
final class TableMapper {

  //constant
  private static final ColumnMapper COLUMN_MAPPER = new ColumnMapper();

  //method
  @SuppressWarnings("unchecked")
  public Table<IEntity> createEmptyTableFromTableDtoForDatabase(
      final ITableDto tableDto,
      final Database database) {
    return Table.withParentDatabaseAndNameAndIdAndEntityClassAndColumns(
        database,
        tableDto.getName(),
        tableDto.getId(),
        (Class<IEntity>) database.internalGetSchema().getEntityTypeByName(tableDto.getName()));
  }

  //method
  public ITable<IEntity> createTableFromTableDtoForDatabaseUsingGivenReferencableTables(
      final ITableDto tableDto,
      final Database database,
      final IContainer<ITable<IEntity>> referencableTables) {

    final var table = createEmptyTableFromTableDtoForDatabase(tableDto, database);

    final var columns = tableDto.getColumns()
        .to(
            c -> COLUMN_MAPPER.createColumnFromDtoForParentTableUsingGivenReferencableTables(c, table,
                referencableTables));

    table.internalSetColumns(columns);

    return table;
  }
}
