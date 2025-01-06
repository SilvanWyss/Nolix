package ch.nolix.system.sqlschema.adapter;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.core.resourcecontrol.resourceclosing.ResourceManager;
import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.IResourceManager;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.systemapi.sqlschemaapi.adapterapi.ISchemaReader;
import ch.nolix.systemapi.sqlschemaapi.flatschemadto.FlatTableDto;
import ch.nolix.systemapi.sqlschemaapi.querycreatorapi.IQueryCreator;
import ch.nolix.systemapi.sqlschemaapi.schemadto.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadto.DataTypeDto;
import ch.nolix.systemapi.sqlschemaapi.schemadto.TableDto;

public final class SchemaReader implements ISchemaReader {

  private final IResourceManager<ISqlConnection> sqlConnection;

  private final IQueryCreator queryCreator;

  private final ICloseController closeController = CloseController.forElement(this);

  private SchemaReader(
    final String databaseName,
    final ISqlConnection sqlConnection,
    final IQueryCreator queryCreator) {

    GlobalValidator.assertThat(queryCreator).thatIsNamed(IQueryCreator.class).isNotNull();

    this.sqlConnection = ResourceManager.forResource(sqlConnection);
    createCloseDependencyTo(sqlConnection);
    this.queryCreator = queryCreator;

    getSqlConnection().executeStatement("USE " + databaseName);
  }

  private SchemaReader(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool,
    final IQueryCreator queryCreator) {

    GlobalValidator.assertThat(queryCreator).thatIsNamed(IQueryCreator.class).isNotNull();

    sqlConnection = ResourceManager.forResourceFromResourcePool(sqlConnectionPool);
    createCloseDependencyTo(sqlConnection);
    this.queryCreator = queryCreator;

    getSqlConnection().executeStatement("USE " + databaseName);
  }

  public static SchemaReader forDatabaseWithNameAndSqlConnectionAndQueryCreator(
    final String databaseName,
    final ISqlConnection sqlConnection,
    final IQueryCreator queryCreator) {
    return //
    new SchemaReader(
      databaseName,
      sqlConnection,
      queryCreator);
  }

  public static SchemaReader forDatabaseWithNameAndSqlConnectionFromSqlConnectionPoolAndQueryCreator(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool,
    final IQueryCreator queryCreator) {
    return //
    new SchemaReader(
      databaseName,
      sqlConnectionPool,
      queryCreator);
  }

  @Override
  public boolean columnsIsEmpty(final String tableName, final String columnName) {
    return //
    getSqlConnection()
      .getRecordsFromQuery(
        queryCreator.createQueryToLoadTopFirstRecordWhereColumnIsNotNull(tableName, columnName))
      .isEmpty();
  }

  @Override
  public ICloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public IContainer<ColumnDto> loadColumns(final String tableName) {
    return //
    getSqlConnection()
      .getRecordsFromQuery(queryCreator.createQueryToLoadNameAndDataTypeOfColumns(tableName))
      .to(
        r -> ColumnDto.withNameAndDataType(r.getStoredAt1BasedIndex(1),
          DataTypeDto.withName(r.getStoredAt1BasedIndex(2))));
  }

  @Override
  public IContainer<FlatTableDto> loadFlatTables() {
    return //
    getSqlConnection()
      .getRecordsHavingSinlgeEntryFromQuery(queryCreator.createQueryToLoadNameOfTables())
      .to(FlatTableDto::new);
  }

  @Override
  public IContainer<TableDto> loadTables() {
    return loadFlatTables().to(t -> new TableDto(t.name(), loadColumns(t.name())));
  }

  @Override
  public void noteClose() {
    sqlConnection.close();
  }

  @Override
  public boolean tableExists(String tableName) {
    return //
    getSqlConnection()
      .getRecordsFromQuery(queryCreator.createQueryToLoadTable(tableName))
      .containsAny();
  }

  private ISqlConnection getSqlConnection() {
    return sqlConnection.get();
  }
}
