//package declaration
package ch.nolix.system.sqldatabaserawschema.schemawriter;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.sql.SqlCollector;
import ch.nolix.coreapi.programcontrolapi.savecontrolapi.ChangeRequestable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedPropertyTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//class
final class SystemDataWriter implements ChangeRequestable {

  // constant
  private static final SystemDataWriterSqlStatementCreator SYSTEM_DATA_WRITER_SQL_STATEMENT_CREATOR = new SystemDataWriterSqlStatementCreator();

  // attribute
  private final SqlCollector sqlCollector;

  // constructor
  public SystemDataWriter(final SqlCollector sqlCollector) {

    GlobalValidator.assertThat(sqlCollector).thatIsNamed(SqlCollector.class).isNotNull();

    this.sqlCollector = sqlCollector;
  }

  // method
  public void addColumn(final String tableName, final IColumnDto column) {
    sqlCollector.addSqlStatement(
        SYSTEM_DATA_WRITER_SQL_STATEMENT_CREATOR.createStatementToAddColumn(tableName, column));
  }

  // method
  public void deleteColumn(String tableName, String columnName) {
    sqlCollector.addSqlStatement(
        SYSTEM_DATA_WRITER_SQL_STATEMENT_CREATOR.createStatementToDeleteColumn(tableName, columnName));
  }

  // method
  public void addTable(final ITableDto table) {
    sqlCollector.addSqlStatements(SYSTEM_DATA_WRITER_SQL_STATEMENT_CREATOR.createStatementsToAddTable(table));
  }

  // method
  public void deleteTable(final String tableName) {
    sqlCollector.addSqlStatement(SYSTEM_DATA_WRITER_SQL_STATEMENT_CREATOR.createStatementToDeleteTable(tableName));
  }

  // method
  @Override
  public boolean hasChanges() {
    return sqlCollector.containsAny();
  }

  // method
  public void setColumnName(final String tableName, final String columnName, final String newColumnName) {
    sqlCollector.addSqlStatement(
        SYSTEM_DATA_WRITER_SQL_STATEMENT_CREATOR.createStatementToSetColumnName(
            tableName,
            columnName,
            newColumnName));
  }

  // method
  public void setColumnParameterizedPropertyType(
      final String columnId,
      final IParameterizedPropertyTypeDto parameterizedPropertyType) {
    sqlCollector.addSqlStatement(
        SYSTEM_DATA_WRITER_SQL_STATEMENT_CREATOR.createStatementToSetColumnParameterizedPropertyType(
            columnId,
            parameterizedPropertyType));
  }

  // method
  public void setSchemaTimestamp(ITime schemaTimestamp) {
    sqlCollector.addSqlStatement(
        SYSTEM_DATA_WRITER_SQL_STATEMENT_CREATOR.createStatementToSetSchemaTimestamp(schemaTimestamp));
  }

  // method
  public void setTableName(final String tableName, final String newTableName) {
    sqlCollector.addSqlStatement(
        SYSTEM_DATA_WRITER_SQL_STATEMENT_CREATOR.createStatementToSetTableName(tableName, newTableName));
  }
}
