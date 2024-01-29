//package declaration
package ch.nolix.system.sqlschema.schemaadapter;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.core.sql.connection.SqlConnection;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
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
  private final SqlConnection sqlConnection;

  //attribute
  private final ISchemaQueryCreator schemaQueryCreator;

  //attribute
  private final CloseController closeController = CloseController.forElement(this);

  //constructor
  private SchemaReader(
    final String databaseName,
    final SqlConnection sqlConnection,
    final ISchemaQueryCreator schemaQueryCreator) {

    GlobalValidator.assertThat(schemaQueryCreator).thatIsNamed(ISchemaQueryCreator.class).isNotNull();

    this.sqlConnection = sqlConnection;
    this.schemaQueryCreator = schemaQueryCreator;

    createCloseDependencyTo(sqlConnection);
    sqlConnection.execute("USE " + databaseName);
  }

  //static method
  public static SchemaReader forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndSchemaQueryCreator(
    final String databaseName,
    final SqlConnectionPool sqlConnectionPool,
    final ISchemaQueryCreator schemaQueryCreator) {
    return new SchemaReader(databaseName, sqlConnectionPool.borrowResource().getStoredSqlConnection(),
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
      .to(r -> new ColumnDto(r.get(0), new DataTypeDto(r.get(1))));
  }

  //method
  @Override
  public IContainer<IFlatTableDto> loadFlatTables() {
    return sqlConnection
      .getRecordsAsStringsFromQuery(schemaQueryCreator.createQueryToLoadNameOfTables())
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
