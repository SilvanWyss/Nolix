//package declaration
package ch.nolix.system.sqldatabasebasicschema.schemaadapter;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.sql.SqlConnection;
import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.sqldatabasebasicschema.flatschemadto.FlatTableDto;
import ch.nolix.system.sqldatabasebasicschema.schemadto.ColumnDto;
import ch.nolix.system.sqldatabasebasicschema.schemadto.DataTypeDto;
import ch.nolix.system.sqldatabasebasicschema.schemadto.TableDto;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.flatschemadtoapi.IFlatTableDto;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.ITableDto;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.sqlsyntaxapi.ISchemaQueryCreator;

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
    return new SchemaReader(databaseName, sqlConnectionPool.borrowSqlConnection(), schemaQueryCreator);
  }

  //method
  @Override
  public boolean columnsIsEmpty(final String tableName, final String columnName) {
    return sqlConnection
      .getRecords(schemaQueryCreator.createQueryToLoadTopFirstRecordWhereColumnIsNotNull(tableName, columnName))
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
      .getRecords(schemaQueryCreator.createQueryToLoadNameAndDataTypeOfColumns(tableName))
      .to(r -> new ColumnDto(r.get(0), new DataTypeDto(r.get(1))));
  }

  //method
  @Override
  public IContainer<IFlatTableDto> loadFlatTables() {
    return sqlConnection
      .getRecordsAsStrings(schemaQueryCreator.createQueryToLoadNameOfTables())
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
      .getRecords(schemaQueryCreator.createQueryToLoadTable(tableName))
      .containsAny();
  }
}
