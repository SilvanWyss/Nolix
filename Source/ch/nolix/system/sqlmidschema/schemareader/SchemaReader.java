package ch.nolix.system.sqlmidschema.schemareader;

import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.sqlmidschema.flatmodelmapper.TableFlatDtoMapper;
import ch.nolix.system.sqlmidschema.modelmapper.ColumnDtoMapper;
import ch.nolix.system.sqlmidschema.modelmapper.TableDtoMapper;
import ch.nolix.system.sqlmidschema.querycreator.QueryCreator;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.midschemaapi.adapterapi.ISchemaReader;
import ch.nolix.systemapi.midschemaapi.flatmodelapi.FlatTableDto;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.midschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.sqlmidschemaapi.flatmodelmapperapi.ITableFlatDtoMapper;
import ch.nolix.systemapi.sqlmidschemaapi.modelmapperapi.IColumnDtoMapper;
import ch.nolix.systemapi.sqlmidschemaapi.modelmapperapi.ITableDtoMapper;
import ch.nolix.systemapi.sqlmidschemaapi.querycreatorapi.IQueryCreator;

public final class SchemaReader implements ISchemaReader {

  private static final IQueryCreator QUERY_CREATOR = new QueryCreator();

  private static final ITableFlatDtoMapper FLAT_TABLE_DTO_MAPPER = new TableFlatDtoMapper();

  private static final IColumnDtoMapper COLUMN_DTO_MAPPER = new ColumnDtoMapper();

  private static final ITableDtoMapper TABLE_DTO_MAPPER = new TableDtoMapper();

  private final ICloseController closeController = CloseController.forElement(this);

  private final ISqlConnection sqlConnection;

  private final ch.nolix.systemapi.sqlschemaapi.adapterapi.ISchemaReader sqlSchemaReader;

  private SchemaReader(
    final String databaseName,
    final ISqlConnection sqlConnection,
    final ch.nolix.systemapi.sqlschemaapi.querycreatorapi.IQueryCreator sqlSchemaQueryCreator) {

    this.sqlSchemaReader = //
    ch.nolix.system.sqlschema.adapter.SchemaReader.forDatabaseNameAndSqlConnectionAndQueryCreator(
      databaseName,
      sqlConnection,
      sqlSchemaQueryCreator);

    this.sqlConnection = sqlConnection;
  }

  public static SchemaReader forDatabaseNameAndSqlConnectionAndSqlSchemaQueryCreator(
    final String databaseName,
    final ISqlConnection sqlConnection,
    final ch.nolix.systemapi.sqlschemaapi.querycreatorapi.IQueryCreator sqlSchemaQueryCreator) {
    return new SchemaReader(databaseName, sqlConnection, sqlSchemaQueryCreator);
  }

  @Override
  public boolean columnIsEmpty(final String tableName, final String columnName) {
    return sqlSchemaReader.columnIsEmpty(tableName, columnName);
  }

  @Override
  public ICloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public int getTableCount() {

    final var query = QUERY_CREATOR.createQueryToGetTableCount();
    final var sqlRecord = sqlConnection.getSingleRecordFromQuery(query);
    final var value = sqlRecord.getStoredOne();

    return Integer.valueOf(value);
  }

  @Override
  public IContainer<ColumnDto> loadColumnsByTableName(final String tableName) {

    final var query = QUERY_CREATOR.createQueryToLoadColumnsByTableName(tableName);
    final var sqlRecords = sqlConnection.getRecordsFromQuery(query);

    return sqlRecords.to(COLUMN_DTO_MAPPER::mapColumnTableSqlRecordToColumnDto);
  }

  @Override
  public IContainer<FlatTableDto> loadFlatTables() {

    final var query = QUERY_CREATOR.createQueryToLoadFlatTables();
    final var sqlRecords = sqlConnection.getRecordsFromQuery(query);

    return sqlRecords.to(FLAT_TABLE_DTO_MAPPER::mapTableTableSqlRecordToFlatTableDto);
  }

  @Override
  public Time loadSchemaTimestamp() {

    final var query = QUERY_CREATOR.createQueryToLoadSchemaTimestamp();
    final var sqlRecord = sqlConnection.getSingleRecordFromQuery(query);
    final var value = sqlRecord.getStoredOne();

    return Time.fromString(value);
  }

  @Override
  public TableDto loadTableByName(final String name) {

    final var query = QUERY_CREATOR.createQueryToLoadJoinedColumns(name);
    final var sqlRecords = sqlConnection.getRecordsFromQuery(query);

    return TABLE_DTO_MAPPER.mapJoinedColumnSqlRecordsToTableDto(sqlRecords);
  }

  @Override
  public IContainer<TableDto> loadTables() {

    final var query = QUERY_CREATOR.createQueryToLoadJoinedColumns();
    final var sqlRecords = sqlConnection.getRecordsFromQuery(query);

    return TABLE_DTO_MAPPER.mapJoinedColumnSqlRecordsToTableDtos(sqlRecords);
  }

  @Override
  public void noteClose() {
    //Does nothing.
  }
}
