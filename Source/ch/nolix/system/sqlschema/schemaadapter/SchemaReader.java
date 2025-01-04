package ch.nolix.system.sqlschema.schemaadapter;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.core.resourcecontrol.resourceclosing.ResourceManager;
import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.IResourceManager;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.systemapi.sqlschemaapi.flatschemadto.FlatTableDto;
import ch.nolix.systemapi.sqlschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.systemapi.sqlschemaapi.schemadto.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadto.DataTypeDto;
import ch.nolix.systemapi.sqlschemaapi.schemadto.TableDto;
import ch.nolix.systemapi.sqlschemaapi.sqlsyntaxapi.ISchemaQueryCreator;

public final class SchemaReader implements ISchemaReader {

  private final IResourceManager<ISqlConnection> sqlConnection;

  private final ISchemaQueryCreator schemaQueryCreator;

  private final ICloseController closeController = CloseController.forElement(this);

  private SchemaReader(
    final String databaseName,
    final ISqlConnection sqlConnection,
    final ISchemaQueryCreator schemaQueryCreator) {

    GlobalValidator.assertThat(schemaQueryCreator).thatIsNamed(ISchemaQueryCreator.class).isNotNull();

    this.sqlConnection = ResourceManager.forResource(sqlConnection);
    this.schemaQueryCreator = schemaQueryCreator;

    createCloseDependencyTo(sqlConnection);
    sqlConnection.executeStatement("USE " + databaseName);
  }

  public static SchemaReader forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndSchemaQueryCreator(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool,
    final ISchemaQueryCreator schemaQueryCreator) {
    return new SchemaReader(
      databaseName,
      sqlConnectionPool.borrowResource(),
      schemaQueryCreator);
  }

  @Override
  public boolean columnsIsEmpty(final String tableName, final String columnName) {
    return //
    getSqlConnection()
      .getRecordsFromQuery(
        schemaQueryCreator.createQueryToLoadTopFirstRecordWhereColumnIsNotNull(tableName, columnName))
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
      .getRecordsFromQuery(schemaQueryCreator.createQueryToLoadNameAndDataTypeOfColumns(tableName))
      .to(
        r -> ColumnDto.withNameAndDataType(r.getStoredAt1BasedIndex(1),
          DataTypeDto.withName(r.getStoredAt1BasedIndex(2))));
  }

  @Override
  public IContainer<FlatTableDto> loadFlatTables() {
    return //
    getSqlConnection()
      .getRecordsHavingSinlgeEntryFromQuery(schemaQueryCreator.createQueryToLoadNameOfTables())
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
      .getRecordsFromQuery(schemaQueryCreator.createQueryToLoadTable(tableName))
      .containsAny();
  }

  private ISqlConnection getSqlConnection() {
    return sqlConnection.get();
  }
}
