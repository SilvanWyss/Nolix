package ch.nolix.system.sqlrawschema.schemawriter;

import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.sql.sqltool.SqlCollector;
import ch.nolix.coreapi.programcontrolapi.savecontrolapi.ChangeRequestable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedFieldTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

final class SystemDataWriter implements ChangeRequestable {

  private static final SystemDataWriterSqlStatementCreator SYSTEM_DATA_WRITER_SQL_STATEMENT_CREATOR = //
  new SystemDataWriterSqlStatementCreator();

  private final SqlCollector sqlCollector;

  public SystemDataWriter(final SqlCollector sqlCollector) {

    GlobalValidator.assertThat(sqlCollector).thatIsNamed(SqlCollector.class).isNotNull();

    this.sqlCollector = sqlCollector;
  }

  public void addColumn(final String tableName, final IColumnDto column) {
    sqlCollector.addSqlStatement(
      SYSTEM_DATA_WRITER_SQL_STATEMENT_CREATOR.createStatementToAddColumn(tableName, column));
  }

  public void deleteColumn(String tableName, String columnName) {
    sqlCollector.addSqlStatement(
      SYSTEM_DATA_WRITER_SQL_STATEMENT_CREATOR.createStatementToDeleteColumn(tableName, columnName));
  }

  public void addTable(final ITableDto table) {
    sqlCollector.addSqlStatements(SYSTEM_DATA_WRITER_SQL_STATEMENT_CREATOR.createStatementsToAddTable(table));
  }

  public void deleteTable(final String tableName) {
    sqlCollector.addSqlStatement(SYSTEM_DATA_WRITER_SQL_STATEMENT_CREATOR.createStatementToDeleteTable(tableName));
  }

  @Override
  public boolean hasChanges() {
    return sqlCollector.containsAny();
  }

  public void setColumnName(final String tableName, final String columnName, final String newColumnName) {
    sqlCollector.addSqlStatement(
      SYSTEM_DATA_WRITER_SQL_STATEMENT_CREATOR.createStatementToSetColumnName(
        tableName,
        columnName,
        newColumnName));
  }

  public void setColumnParameterizedFieldType(
    final String columnId,
    final IParameterizedFieldTypeDto parameterizedFieldType) {
    sqlCollector.addSqlStatement(
      SYSTEM_DATA_WRITER_SQL_STATEMENT_CREATOR.createStatementToSetColumnParameterizedFieldType(
        columnId,
        parameterizedFieldType));
  }

  public void setSchemaTimestamp(ITime schemaTimestamp) {
    sqlCollector.addSqlStatement(
      SYSTEM_DATA_WRITER_SQL_STATEMENT_CREATOR.createStatementToSetSchemaTimestamp(schemaTimestamp));
  }

  public void setTableName(final String tableName, final String newTableName) {
    sqlCollector.addSqlStatement(
      SYSTEM_DATA_WRITER_SQL_STATEMENT_CREATOR.createStatementToSetTableName(tableName, newTableName));
  }
}
