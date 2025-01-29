package ch.nolix.system.sqlschema.statementcreator;

import ch.nolix.coreapi.programatomapi.stringcatalogapi.StringCatalog;
import ch.nolix.systemapi.sqlschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.modelapi.ConstraintDto;
import ch.nolix.systemapi.sqlschemaapi.modelapi.DataTypeDto;
import ch.nolix.systemapi.sqlschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.sqlschemaapi.statementcreatorapi.IStatementCreator;

public final class StatementCreator implements IStatementCreator {

  @Override
  public String createStatementToAddColumn(final String tabbleName, final ColumnDto column) {
    return ("ALTER TABLE " + tabbleName + " ADD " + getColumnAsSql(column) + ";");
  }

  @Override
  public String createStatementToAddTable(TableDto table) {
    return "CREATE TABLE "
    + table.name()
    + " (" + table.columns().to(this::getColumnAsSql).toStringWithSeparator(", ")
    + ");";
  }

  @Override
  public String createStatementToDeleteColumn(final String tableName, final String columnName) {
    return ("ALTER TABLE " + tableName + " DROP COLUMN " + columnName + ";");
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

  private String getConstraintAsSql(final ConstraintDto constraint) {

    var sql = constraint.type().toString().replace(StringCatalog.UNDERSCORE, StringCatalog.SPACE);

    if (constraint.parameters().containsAny()) {
      getConstraintParametersAsSql(constraint);
    }

    return sql;
  }

  private String getConstraintsAsSql(final ColumnDto column) {
    return column.constraints().to(this::getConstraintAsSql).toStringWithSeparator(",");
  }

  private String getConstraintParametersAsSql(final ConstraintDto constraint) {
    return ("(" + constraint.parameters().toStringWithSeparator(",") + ")");
  }

  private String getDataTypeAsSql(final DataTypeDto dataType) {

    final var optionalParameter = dataType.optionalParameter();

    if (optionalParameter.isPresent()) {
      return (dataType.name() + "(" + optionalParameter.get() + ")");
    }

    return dataType.name();
  }
}
