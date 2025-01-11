package ch.nolix.system.sqlrawschema.schemareader;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.core.sql.connection.SqlConnection;
import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.sqlrawschema.querycreator.QueryCreator;
import ch.nolix.system.sqlrawschema.rawschemadtomapper.ColumnDtoMapper;
import ch.nolix.system.sqlrawschema.rawschemaflatdtomapper.TableFlatDtoMapper;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawschemaapi.adapterapi.ISchemaReader;
import ch.nolix.systemapi.rawschemaapi.dto.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.dto.TableDto;
import ch.nolix.systemapi.rawschemaapi.flatdto.FlatTableDto;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.TableType;
import ch.nolix.systemapi.sqlrawschemaapi.querycreatorapi.IQueryCreator;
import ch.nolix.systemapi.sqlrawschemaapi.rawschemaflatdtomapperapi.ITableFlatDtoMapper;
import ch.nolix.systemapi.sqlschemaapi.adapterapi.ISchemaAdapter;

public final class SchemaReader implements ISchemaReader {

  private static final IQueryCreator QUERY_CREATOR = new QueryCreator();

  private static final ITableFlatDtoMapper TABLE_DTO_MAPPER = new TableFlatDtoMapper();

  private static final ColumnDtoMapper COLUMN_DTO_MAPPER = new ColumnDtoMapper();

  private final ICloseController closeController = CloseController.forElement(this);

  private final ISqlConnection sqlConnection;

  private final ISchemaAdapter schemaAdapter;

  private SchemaReader(
    final String databaseName,
    final ISqlConnection sqlConnection,
    final ISchemaAdapter schemaAdapter) {

    GlobalValidator.assertThat(sqlConnection).thatIsNamed(SqlConnection.class).isNotNull();
    GlobalValidator.assertThat(schemaAdapter).thatIsNamed(ISchemaAdapter.class).isNotNull();

    this.sqlConnection = sqlConnection;
    this.schemaAdapter = schemaAdapter;

    createCloseDependencyTo(sqlConnection);
    createCloseDependencyTo(schemaAdapter);

    sqlConnection.executeStatement("USE " + databaseName);
  }

  public static SchemaReader forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndSchemaAdapter(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool,
    final ISchemaAdapter schemaAdapter) {
    return new SchemaReader(databaseName, sqlConnectionPool.borrowResource(), schemaAdapter);
  }

  @Override
  public boolean columnIsEmpty(final String tableName, final String columnName) {
    return schemaAdapter.columnsIsEmpty(TableType.ENTITY_TABLE.getQualifyingPrefix() + tableName, columnName);
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
