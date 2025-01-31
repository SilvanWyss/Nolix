package ch.nolix.system.sqlrawschema.schemareader;

import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.sqlrawschema.querycreator.QueryCreator;
import ch.nolix.system.sqlrawschema.rawschemadtomapper.ColumnDtoMapper;
import ch.nolix.system.sqlrawschema.rawschemadtomapper.TableReferenceDtoMapper;
import ch.nolix.system.sqlrawschema.rawschemaflatdtomapper.TableFlatDtoMapper;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawschemaapi.adapterapi.ISchemaReader;
import ch.nolix.systemapi.rawschemaapi.flatmodelapi.FlatTableDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableReferenceDto;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.TableNameQualifyingPrefix;
import ch.nolix.systemapi.sqlrawschemaapi.querycreatorapi.IQueryCreator;
import ch.nolix.systemapi.sqlrawschemaapi.rawschemadtomapperapi.IColumnDtoMapper;
import ch.nolix.systemapi.sqlrawschemaapi.rawschemadtomapperapi.ITableReferenceDtoMapper;
import ch.nolix.systemapi.sqlrawschemaapi.rawschemaflatdtomapperapi.ITableFlatDtoMapper;

public final class SchemaReader implements ISchemaReader {

  private static final IQueryCreator QUERY_CREATOR = new QueryCreator();

  private static final ITableFlatDtoMapper TABLE_DTO_MAPPER = new TableFlatDtoMapper();

  private static final IColumnDtoMapper COLUMN_DTO_MAPPER = new ColumnDtoMapper();

  private static final ITableReferenceDtoMapper TABLE_REFERENCE_DTO_MAPPER = new TableReferenceDtoMapper();

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
    return //
    sqlSchemaReader.columnsIsEmpty(TableNameQualifyingPrefix.E + tableName, columnName);
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
  public IContainer<ColumnDto> loadColumnsByTableId(final String tableId) {

    final var query = QUERY_CREATOR.createQueryToLoadCoumnsByTableId(tableId);
    final var sqlRecords = sqlConnection.getRecordsFromQuery(query);

    return //
    sqlRecords.to(
      r -> COLUMN_DTO_MAPPER.mapColumnTableSqlRecordToColumnDto(r, loadTableReferencesByColumnId(r.getStoredFirst())));
  }

  @Override
  public IContainer<ColumnDto> loadColumnsByTableName(final String tableName) {

    final var query = QUERY_CREATOR.createQueryToLoadCoumnsByTableName(tableName);
    final var sqlRecords = sqlConnection.getRecordsFromQuery(query);

    return //
    sqlRecords.to(
      r -> COLUMN_DTO_MAPPER.mapColumnTableSqlRecordToColumnDto(r, loadTableReferencesByColumnId(r.getStoredFirst())));
  }

  @Override
  public FlatTableDto loadFlatTableById(final String id) {

    final var query = QUERY_CREATOR.createQueryToLoadFlatTableById(id);
    final var sqlRecord = sqlConnection.getSingleRecordFromQuery(query);

    return TABLE_DTO_MAPPER.mapTableTableSqlRecordToFlatTableDto(sqlRecord);
  }

  @Override
  public FlatTableDto loadFlatTableByName(final String name) {

    final var query = QUERY_CREATOR.createQueryToLoadFlatTableByName(name);
    final var sqlRecord = sqlConnection.getSingleRecordFromQuery(query);

    return TABLE_DTO_MAPPER.mapTableTableSqlRecordToFlatTableDto(sqlRecord);
  }

  @Override
  public IContainer<FlatTableDto> loadFlatTables() {

    final var query = QUERY_CREATOR.createQueryToLoadFlatTables();
    final var sqlRecords = sqlConnection.getRecordsFromQuery(query);

    return sqlRecords.to(TABLE_DTO_MAPPER::mapTableTableSqlRecordToFlatTableDto);
  }

  @Override
  public Time loadSchemaTimestamp() {

    final var query = QUERY_CREATOR.createQueryToLoadSchemaTimestamp();
    final var sqlRecord = sqlConnection.getSingleRecordFromQuery(query);
    final var value = sqlRecord.getStoredOne();

    return Time.fromString(value);
  }

  @Override
  public TableDto loadTableById(final String id) {
    return loadTable(loadFlatTableById(id));
  }

  @Override
  public TableDto loadTableByName(final String name) {
    return loadTable(loadFlatTableByName(name));
  }

  @Override
  public IContainer<TableReferenceDto> loadTableReferencesByColumnId(final String columnId) {

    final var query = QUERY_CREATOR.createQueryToLoadTableReferences(columnId);
    final var sqlRecords = sqlConnection.getRecordsFromQuery(query);

    return sqlRecords.to(TABLE_REFERENCE_DTO_MAPPER::mapTableReferenceTableSqlRecordToTableReferenceDto);
  }

  @Override
  public IContainer<TableDto> loadTables() {
    return loadFlatTables().to(t -> loadTableById(t.id()));
  }

  @Override
  public void noteClose() {
    //Does nothing.
  }

  private TableDto loadTable(final FlatTableDto flatTable) {
    return new TableDto(flatTable.id(), flatTable.name(), loadColumnsByTableId(flatTable.id()));
  }
}
