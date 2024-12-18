package ch.nolix.system.sqlschema.sqlsyntax;

import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;
import ch.nolix.systemapi.sqlschemaapi.schemadto.DataTypeDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IConstraintDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDto;
import ch.nolix.systemapi.sqlschemaapi.sqlsyntaxapi.ISchemaStatementCreator;

public final class SchemaStatementCreator implements ISchemaStatementCreator {

  @Override
  public String createStatementToAddColumn(final String tabbleName, final IColumnDto column) {
    return ("ALTER TABLE " + tabbleName + " ADD " + getColumnAsSql(column) + ";");
  }

  @Override
  public String createStatementToAddTable(ITableDto table) {
    return "CREATE TABLE "
    + table.getName()
    + " (" + table.getColumns().to(this::getColumnAsSql).toStringWithSeparator(",")
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

  private String getColumnAsSql(final IColumnDto column) {

    var sql = column.getName() + " " + getDataTypeAsSql(column.getDataType());

    if (column.getConstraints().containsAny()) {
      sql += getConstraintsAsSql(column);
    }

    return sql;
  }

  private String getConstraintAsSql(final IConstraintDto constraint) {

    var sql = constraint.getType().toString().replace(StringCatalogue.UNDERSCORE, StringCatalogue.SPACE);

    if (constraint.getParameters().containsAny()) {
      getConstraintParametersAsSql(constraint);
    }

    return sql;
  }

  private String getConstraintsAsSql(final IColumnDto column) {
    return column.getConstraints().to(this::getConstraintAsSql).toStringWithSeparator(",");
  }

  private String getConstraintParametersAsSql(final IConstraintDto constraint) {
    return ("(" + constraint.getParameters().toStringWithSeparator(",") + ")");
  }

  private String getDataTypeAsSql(final DataTypeDto dataType) {

    final var optionalParameter = dataType.optionalParameter();

    if (optionalParameter.isPresent()) {
      return (dataType.name() + "(" + optionalParameter.get() + ")");
    }

    return dataType.name();
  }
}
