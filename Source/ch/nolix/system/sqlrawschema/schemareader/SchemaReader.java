package ch.nolix.system.sqlrawschema.schemareader;

import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.sqlrawschema.querycreator.QueryCreator;
import ch.nolix.system.sqlrawschema.rawschemadtomapper.ColumnDtoMapper;
import ch.nolix.system.sqlrawschema.rawschemaflatdtomapper.TableFlatDtoMapper;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawschemaapi.adapterapi.ISchemaReader;
import ch.nolix.systemapi.rawschemaapi.flatmodelapi.FlatTableDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.TableType;
import ch.nolix.systemapi.sqlrawschemaapi.querycreatorapi.IQueryCreator;
import ch.nolix.systemapi.sqlrawschemaapi.rawschemaflatdtomapperapi.ITableFlatDtoMapper;

public final class SchemaReader implements ISchemaReader {

  private static final IQueryCreator QUERY_CREATOR = new QueryCreator();

  private static final ITableFlatDtoMapper TABLE_DTO_MAPPER = new TableFlatDtoMapper();

  private static final ColumnDtoMapper COLUMN_DTO_MAPPER = new ColumnDtoMapper();

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
    return sqlSchemaReader.columnsIsEmpty(TableType.ENTITY_TABLE.getQualifyingPrefix() + tableName, columnName);
  }

  @Override
  public ICloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public int getTableCount() {
    return Integer.valueOf(
      sqlConnection
        .getSingleRecordFromQuery(QUERY_CREATOR.createQueryToGetTableCount())
        .getStoredAt1BasedIndex(1));
  }

  @Override
  public IContainer<ColumnDto> loadColumnsByTableId(final String tableId) {
    return sqlConnection
      .getRecordsFromQuery(QUERY_CREATOR.createQueryToLoadCoumnsByTableId(tableId))
      .to(COLUMN_DTO_MAPPER::mapColumnTableSqlRecordToColumnDto);
  }

  @Override
  public IContainer<ColumnDto> loadColumnsByTableName(final String tableName) {
    return sqlConnection
      .getRecordsFromQuery(QUERY_CREATOR.createQueryToLoadCoumnsByTableName(tableName))
      .to(COLUMN_DTO_MAPPER::mapColumnTableSqlRecordToColumnDto);
  }

  @Override
  public FlatTableDto loadFlatTableById(final String id) {
    return TABLE_DTO_MAPPER.createTableDto(
      sqlConnection.getSingleRecordFromQuery(QUERY_CREATOR.createQueryToLoadFlatTableById(id)));
  }

  @Override
  public FlatTableDto loadFlatTableByName(final String name) {
    return TABLE_DTO_MAPPER.createTableDto(
      sqlConnection.getSingleRecordFromQuery(QUERY_CREATOR.createQueryToLoadFlatTableByName(name)));
  }

  @Override
  public IContainer<FlatTableDto> loadFlatTables() {
    return sqlConnection
      .getRecordsFromQuery(QUERY_CREATOR.createQueryToLoadFlatTables())
      .to(TABLE_DTO_MAPPER::createTableDto);
  }

  @Override
  public Time loadSchemaTimestamp() {
    return Time.fromString(
      sqlConnection
        .getRecordsFromQuery(QUERY_CREATOR.createQueryToLoadSchemaTimestamp())
        .getStoredFirst()
        .getStoredAt1BasedIndex(1));
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
  public IContainer<TableDto> loadTables() {
    return loadFlatTables().to(t -> loadTableById(t.id()));
  }

  @Override
  public void noteClose() {
    //Does nothing.
  }

  private TableDto loadTable(final FlatTableDto flatTable) {
    return new TableDto(
      flatTable.id(),
      flatTable.name(),
      loadColumnsByTableId(flatTable.id()));
  }
}
