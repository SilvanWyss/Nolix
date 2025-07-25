package ch.nolix.system.sqlschema.statementcreator;

import ch.nolix.coreapi.programatom.stringcatalog.StringCatalog;
import ch.nolix.systemapi.sqlschema.model.ColumnConstraintDto;
import ch.nolix.systemapi.sqlschema.model.ColumnDto;
import ch.nolix.systemapi.sqlschema.model.DataTypeDto;
import ch.nolix.systemapi.sqlschema.model.TableDto;
import ch.nolix.systemapi.sqlschema.statementcreator.IStatementCreator;

public final class StatementCreator implements IStatementCreator {

  @Override
  public String createStatementToAddColumn(final String tabbleName, final ColumnDto column) {
    return ("ALTER TABLE " + tabbleName + " ADD " + getColumnAsSql(column) + ";");
  }

  @Override
  public String createStatementToAddTable(TableDto table) {
    return //
    "CREATE TABLE "
    + table.name()
    + " (" + table.columns().to(this::getColumnAsSql).toStringWithSeparator(", ")
    + ");";
  }

  @Override
  public String createStatementToDeleteColumn(final String tableName, final String columnName) {
    return ("ALTER TABLE " + tableName + " DROP COLUMN " + columnName + ";");
  }

  @Override
  public String createStatementToDeleteColumnIfExists(final String tableName, final String columnName) {
    return //
    "IF EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME ='"
    + tableName
    + "' AND COLUMN_NAME = '"
    + columnName
    + "')"
    + "BEGIN ALTER TABLE "
    + tableName
    + " DROP COLUMN "
    + columnName
    + " END;";
  }

  @Override
  public String createStatementToDeleteTable(final String tableName) {
    return ("DROP TABLE " + tableName + ";");
  }

  @Override
  public String createStatementToRenameColumn(
    final String tableName,
    final String columnName,
    final String newColumnName) {
    return ("ALTER TABLE " + tableName + " RENAME COLUMN " + columnName + " TO " + newColumnName + ";");
  }

  @Override
  public String createStatementToRenameColumnIfExists(
    final String tableName,
    final String columnName,
    final String newColumnName) {
    return //
    "IF EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME ='"
    + tableName
    + "' AND COLUMN_NAME = '"
    + columnName
    + "')"
    + "BEGIN ALTER TABLE "
    + tableName
    + " RENAME COLUMN "
    + columnName
    + " TO "
    + newColumnName
    + " END;";
  }

  @Override
  public String createStatementToRenameTable(final String tableName, final String newTableName) {
    return ("ALTER TABLE " + tableName + " RENAME TO " + newTableName + ";");
  }

  private String getColumnAsSql(final ColumnDto column) {

    var sql = column.name() + " " + getDataTypeAsSql(column.dataType());

    if (column.constraints().containsAny()) {
      sql += getConstraintsAsSql(column);
    }

    return sql;
  }

  private String getConstraintAsSql(final ColumnConstraintDto constraint) {

    var sql = constraint.constraint().toString().replace(StringCatalog.UNDERSCORE, StringCatalog.SPACE);

    if (constraint.parameters().containsAny()) {
      getConstraintParametersAsSql(constraint);
    }

    return sql;
  }

  private String getConstraintsAsSql(final ColumnDto column) {
    return column.constraints().to(this::getConstraintAsSql).toStringWithSeparator(",");
  }

  private String getConstraintParametersAsSql(final ColumnConstraintDto constraint) {
    return ("(" + constraint.parameters().toStringWithSeparator(",") + ")");
  }

  private String getDataTypeAsSql(final DataTypeDto dataType) {

    final var parameter = dataType.nullableParameter();

    if (parameter != null) {
      return (dataType.name() + "(" + parameter + ")");
    }

    return dataType.name();
  }
}
