//package declaration
package ch.nolix.system.sqlschema.schemaadapter;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.core.sql.connectionpool.SqlConnectionPool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.sqlschema.flatschemadto.FlatTableDto;
import ch.nolix.system.sqlschema.schemadto.ColumnDto;
import ch.nolix.system.sqlschema.schemadto.DataTypeDto;
import ch.nolix.system.sqlschema.schemadto.TableDto;
import ch.nolix.systemapi.sqlschemaapi.flatschemadtoapi.IFlatTableDto;
import ch.nolix.systemapi.sqlschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDto;
import ch.nolix.systemapi.sqlschemaapi.sqlsyntaxapi.ISchemaQueryCreator;

//class
final class SchemaReader implements ISchemaReader {

  //attribute
  private final ISqlConnection sqlConnection;

  //attribute
  private final ISchemaQueryCreator schemaQueryCreator;

  //attribute
  private final CloseController closeController = CloseController.forElement(this);

  //constructor
  private SchemaReader(
    final String databaseName,
    final ISqlConnection sqlConnection,
    final ISchemaQueryCreator schemaQueryCreator) {

    GlobalValidator.assertThat(schemaQueryCreator).thatIsNamed(ISchemaQueryCreator.class).isNotNull();

    this.sqlConnection = sqlConnection;
    this.schemaQueryCreator = schemaQueryCreator;

    createCloseDependencyTo(sqlConnection);
    sqlConnection.executeStatement("USE " + databaseName);
  }

  //static method
  public static SchemaReader forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndSchemaQueryCreator(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool,
    final ISchemaQueryCreator schemaQueryCreator) {
    return new SchemaReader(
      databaseName,
      sqlConnectionPool.borrowResource(),
      schemaQueryCreator);
  }

  //method
  @Override
  public boolean columnsIsEmpty(final String tableName, final String columnName) {
    return sqlConnection
      .getRecordsFromQuery(
        schemaQueryCreator.createQueryToLoadTopFirstRecordWhereColumnIsNotNull(tableName, columnName))
      .isEmpty();
  }

  //method
  @Override
  public CloseController getStoredCloseController() {
    return closeController;
  }

  //method
  @Override
  public IContainer<IColumnDto> loadColumns(final String tableName) {
    return sqlConnection
      .getRecordsFromQuery(schemaQueryCreator.createQueryToLoadNameAndDataTypeOfColumns(tableName))
      .to(r -> new ColumnDto(r.getStoredAt1BasedIndex(1), new DataTypeDto(r.getStoredAt1BasedIndex(2))));
  }

  //method
  @Override
  public IContainer<IFlatTableDto> loadFlatTables() {
    return sqlConnection
      .getRecordsHavingSinlgeEntryFromQuery(schemaQueryCreator.createQueryToLoadNameOfTables())
      .to(FlatTableDto::new);
  }

  //method
  @Override
  public IContainer<ITableDto> loadTables() {
    return loadFlatTables().to(t -> new TableDto(t.getName(), loadColumns(t.getName())));
  }

  //method
  @Override
  public void noteClose() {
    //Does nothing.
  }

  //method
  @Override
  public boolean tableExists(String tableName) {
    return sqlConnection
      .getRecordsFromQuery(schemaQueryCreator.createQueryToLoadTable(tableName))
      .containsAny();
  }
}
