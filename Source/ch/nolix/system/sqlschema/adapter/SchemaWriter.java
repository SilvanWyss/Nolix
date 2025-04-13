package ch.nolix.system.sqlschema.adapter;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.programcontrol.closepool.CloseController;
import ch.nolix.core.resourcecontrol.resourcevalidator.ResourceValidator;
import ch.nolix.core.sql.sqltool.SqlCollector;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.coreapi.sqlapi.connectionapi.ISqlConnection;
import ch.nolix.system.sqlschema.statementcreator.StatementCreator;
import ch.nolix.systemapi.sqlschemaapi.adapterapi.ISchemaWriter;
import ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.sqlschemaapi.statementcreatorapi.IStatementCreator;

public final class SchemaWriter implements ISchemaWriter {

  private static final IStatementCreator STATEMENT_CREATOR = new StatementCreator();

  private final ICloseController closeController = CloseController.forElement(this);

  private final ISqlConnection sqlConnection;

  private final SqlCollector sqlCollector = new SqlCollector();

  private int saveCount;

  private SchemaWriter(final String databaseName, final ISqlConnection sqlConnection) {

    Validator.assertThat(databaseName).thatIsNamed(LowerCaseVariableCatalog.DATABASE_NAME).isNotBlank();
    ResourceValidator.assertIsOpen(sqlConnection);

    this.sqlConnection = sqlConnection;
    createCloseDependencyTo(sqlConnection);

    sqlCollector.addSqlStatement("USE " + databaseName);
  }

  public static SchemaWriter forDatabasNameAndSqlConnection(
    final String databaseName,
    final ISqlConnection sqlConnection) {
    return new SchemaWriter(databaseName, sqlConnection);
  }

  @Override
  public void addColumn(final String tableName, final ColumnDto column) {

    final var statement = STATEMENT_CREATOR.createStatementToAddColumn(tableName, column);

    sqlCollector.addSqlStatement(statement);
  }

  @Override
  public void addTable(final TableDto table) {

    final var statement = STATEMENT_CREATOR.createStatementToAddTable(table);

    sqlCollector.addSqlStatement(statement);
  }

  @Override
  public void deleteColumn(final String tableName, final String columnName) {

    final var statement = STATEMENT_CREATOR.createStatementToDeleteColumn(tableName, columnName);

    sqlCollector.addSqlStatement(statement);
  }

  @Override
  public void deleteTable(final String tableName) {

    final var statement = STATEMENT_CREATOR.createStatementToDeleteTable(tableName);

    sqlCollector.addSqlStatement(statement);
  }

  @Override
  public ICloseController getStoredCloseController() {
    return closeController;
  }

  @Override
  public int getSaveCount() {
    return saveCount;
  }

  @Override
  public IContainer<String> getSqlStatements() {
    return sqlCollector.getSqlStatements();
  }

  @Override
  public boolean hasChanges() {
    return sqlCollector.containsAny();
  }

  @Override
  public void noteClose() {
    //Does nothing.
  }

  @Override
  public void renameColumn(final String tableName, final String columnName, final String newColumnName) {

    final var statement = STATEMENT_CREATOR.createStatementToRenameColumn(tableName, columnName, newColumnName);

    sqlCollector.addSqlStatement(statement);
  }

  @Override
  public void renameTable(final String tableName, final String newTableName) {

    final var statement = STATEMENT_CREATOR.createStatementToRenameTable(tableName, newTableName);

    sqlCollector.addSqlStatement(statement);
  }

  @Override
  public void reset() {
    sqlCollector.clear();
  }

  @Override
  public void saveChanges() {
    try {
      sqlCollector.executeAndClearUsingConnection(sqlConnection);
      saveCount++;
    } finally {
      reset();
    }
  }
}
