//package declaration
package ch.nolix.system.sqldatabaserawschema.schemareader;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.core.sql.SqlConnection;
import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectschema.schemadto.SaveStampConfigurationDto;
import ch.nolix.system.objectschema.schemadto.TableDto;
import ch.nolix.system.sqldatabaserawschema.columntable.ColumnDtoMapper;
import ch.nolix.system.sqldatabaserawschema.structure.TableType;
import ch.nolix.system.sqldatabaserawschema.tabletable.TableDtoMapper;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDto;
import ch.nolix.systemapi.rawschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.SaveStampStrategy;
import ch.nolix.systemapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class SchemaReader implements ISchemaReader {

  //constant
  private static final QueryCreator QUERY_CREATOR = new QueryCreator();

  //constant
  private static final TableDtoMapper TABLE_DTO_MAPPER = new TableDtoMapper();

  //constant
  private static final ColumnDtoMapper COLUMN_DTO_MAPPER = new ColumnDtoMapper();

  //attribute
  private final CloseController closeController = CloseController.forElement(this);

  //attribute
  private final SqlConnection sqlConnection;

  //attribute
  private final ISchemaAdapter schemaAdapter;

  //constructor
  private SchemaReader(
    final String databaseName,
    final SqlConnection sqlConnection,
    final ISchemaAdapter schemaAdapter) {

    GlobalValidator.assertThat(sqlConnection).thatIsNamed(SqlConnection.class).isNotNull();
    GlobalValidator.assertThat(schemaAdapter).thatIsNamed(ISchemaAdapter.class).isNotNull();

    this.sqlConnection = sqlConnection;
    this.schemaAdapter = schemaAdapter;

    createCloseDependencyTo(sqlConnection);
    createCloseDependencyTo(schemaAdapter);

    sqlConnection.execute("USE " + databaseName);
  }

  //static method
  public static SchemaReader forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndSchemaAdapter(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool,
    final ISchemaAdapter schemaAdapter) {
    return new SchemaReader(databaseName, sqlConnectionPool.borrowSqlConnection(), schemaAdapter);
  }

  //method
  @Override
  public boolean columnIsEmpty(final String tableName, final String columnName) {
    return schemaAdapter.columnsIsEmpty(TableType.ENTITY_TABLE.getNamePrefix() + tableName, columnName);
  }

  //method
  @Override
  public CloseController getStoredCloseController() {
    return closeController;
  }

  //method
  @Override
  public int getTableCount() {
    return Integer.valueOf(sqlConnection.getOneRecord(QUERY_CREATOR.createQueryToGetTableCount()).get(0));
  }

  //method
  @Override
  public IContainer<IColumnDto> loadColumnsByTableId(final String tableId) {
    return sqlConnection
      .getRecords(QUERY_CREATOR.createQueryToLoadCoumnsByTableId(tableId))
      .to(COLUMN_DTO_MAPPER::createColumnDto);
  }

  //method
  @Override
  public IContainer<IColumnDto> loadColumnsByTableName(final String tableName) {
    return sqlConnection
      .getRecords(QUERY_CREATOR.createQueryToLoadCoumnsByTableName(tableName))
      .to(COLUMN_DTO_MAPPER::createColumnDto);
  }

  //method
  @Override
  public IFlatTableDto loadFlatTableById(final String id) {
    return TABLE_DTO_MAPPER.createTableDto(
      sqlConnection.getOneRecord(QUERY_CREATOR.createQueryToLoadFlatTableById(id)));
  }

  //method
  @Override
  public IFlatTableDto loadFlatTableByName(final String name) {
    return TABLE_DTO_MAPPER.createTableDto(
      sqlConnection.getOneRecord(QUERY_CREATOR.createQueryToLoadFlatTableByName(name)));
  }

  //method
  @Override
  public IContainer<IFlatTableDto> loadFlatTables() {
    return sqlConnection
      .getRecords(QUERY_CREATOR.createQueryToLoadFlatTables())
      .to(TABLE_DTO_MAPPER::createTableDto);
  }

  //method
  @Override
  public Time loadSchemaTimestamp() {
    return Time.fromString(
      sqlConnection.getRecords(QUERY_CREATOR.createQueryToLoadSchemaTimestamp()).getStoredFirst().get(0));
  }

  //method
  @Override
  public ITableDto loadTableById(final String id) {
    return loadTable(loadFlatTableById(id));
  }

  //method
  @Override
  public ITableDto loadTableByName(final String name) {
    return loadTable(loadFlatTableByName(name));
  }

  //method
  @Override
  public IContainer<ITableDto> loadTables() {
    return loadFlatTables().to(t -> loadTableById(t.getId()));
  }

  //method
  @Override
  public void noteClose() {
    //Does nothing.
  }

  //method
  private ITableDto loadTable(final IFlatTableDto flatTable) {
    return new TableDto(
      flatTable.getId(),
      flatTable.getName(),
      new SaveStampConfigurationDto(SaveStampStrategy.OWN_SAVE_STAMP),
      loadColumnsByTableId(flatTable.getId()));
  }
}
