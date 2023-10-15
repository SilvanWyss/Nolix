//package declaration
package ch.nolix.system.sqldatabasebasicschema.schemaadapter;

//own imports
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.sql.SqlConnectionPool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.flatschemadtoapi.IFlatTableDto;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemaadapterapi.ISchemaAdapter;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemaadapterapi.ISchemaReader;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemaadapterapi.ISchemaWriter;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.schemadtoapi.ITableDto;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.sqlsyntaxapi.ISchemaQueryCreator;
import ch.nolix.systemapi.sqldatabasebasicschemaapi.sqlsyntaxapi.ISchemaStatementCreator;

//class
public abstract class SchemaAdapter implements ISchemaAdapter {

  // attribute
  private final ISchemaReader schemaReader;

  // attribute
  private final ISchemaWriter schemaWriter;

  // attribute
  private final CloseController closeController = CloseController.forElement(this);

  // constructor
  protected SchemaAdapter(
      final String databaseName,
      final SqlConnectionPool sqlConnectionPool,
      final ISchemaQueryCreator schemaQueryCreator,
      final ISchemaStatementCreator schemaStatementCreator) {

    schemaReader = SchemaReader.forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndSchemaQueryCreator(
        databaseName,
        sqlConnectionPool,
        schemaQueryCreator);

    schemaWriter = SchemaWriter.forDatabaseWithGivenNameUsingConnectionFromGivenPoolAndSchemaStatementCreator(
        databaseName,
        sqlConnectionPool,
        schemaStatementCreator);

    getStoredCloseController().createCloseDependencyTo(schemaReader);
    getStoredCloseController().createCloseDependencyTo(schemaWriter);
  }

  // method
  @Override
  public final void addColumn(final String tableName, final IColumnDto column) {
    schemaWriter.addColumn(tableName, column);
  }

  // method
  @Override
  public final void addTable(final ITableDto table) {
    schemaWriter.addTable(table);
  }

  // method
  @Override
  public final boolean columnsIsEmpty(final String tableName, final String columnName) {
    return schemaReader.columnsIsEmpty(tableName, columnName);
  }

  // method
  @Override
  public final void deleteColumn(final String tableName, final String columnName) {
    schemaWriter.deleteColumn(tableName, columnName);
  }

  // method
  @Override
  public final void deleteTable(final String tableName) {
    schemaWriter.deleteTable(tableName);
  }

  // method
  @Override
  public final CloseController getStoredCloseController() {
    return closeController;
  }

  // method
  @Override
  public final int getSaveCount() {
    return schemaWriter.getSaveCount();
  }

  // method
  @Override
  public final IContainer<String> getSqlStatements() {
    return schemaWriter.getSqlStatements();
  }

  // method
  @Override
  public final boolean hasChanges() {
    return schemaWriter.hasChanges();
  }

  // method
  @Override
  public final IContainer<IColumnDto> loadColumns(final String tableName) {
    return schemaReader.loadColumns(tableName);
  }

  // method
  @Override
  public final IContainer<IFlatTableDto> loadFlatTables() {
    return schemaReader.loadFlatTables();
  }

  // method
  @Override
  public final IContainer<ITableDto> loadTables() {
    return schemaReader.loadTables();
  }

  // method
  @Override
  public final void noteClose() {
  }

  // method
  @Override
  public final void renameColumn(final String tableName, final String columnName, final String newColumnName) {
    schemaWriter.renameColumn(tableName, columnName, newColumnName);
  }

  // method
  @Override
  public final void renameTable(final String tableName, final String newTableName) {
    schemaWriter.renameTable(tableName, newTableName);
  }

  // method
  @Override
  public final void reset() {
    schemaWriter.reset();
  }

  // method
  @Override
  public final void saveChanges() {
    schemaWriter.saveChanges();
  }

  // method
  @Override
  public final boolean tableExists(final String tableName) {
    return schemaReader.tableExists(tableName);
  }
}
