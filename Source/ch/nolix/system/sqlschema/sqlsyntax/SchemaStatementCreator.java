//package declaration
package ch.nolix.system.sqlschema.sqlsyntax;

import ch.nolix.coreapi.commontypetoolapi.stringutilapi.StringCatalogue;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IConstraintDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IDataTypeDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDto;
import ch.nolix.systemapi.sqlschemaapi.sqlsyntaxapi.ISchemaStatementCreator;

//class
public final class SchemaStatementCreator implements ISchemaStatementCreator {

  //method
  @Override
  public String createStatementToAddColumn(final String tabbleName, final IColumnDto column) {
    return ("ALTER TABLE " + tabbleName + " ADD " + getColumnAsSql(column) + ";");
  }

  //method
  @Override
  public String createStatementToAddTable(ITableDto table) {
    return "CREATE TABLE "
    + table.getName()
    + " (" + table.getColumns().to(this::getColumnAsSql).toStringWithSeparator(",")
    + ");";
  }

  //method
  @Override
  public String createStatementToDeleteColumn(final String tableName, final String columnName) {
    return ("ALTER TABLE " + tableName + " DROP COLUMN " + columnName + ";");
  }

  //method
  @Override
  public String createStatementToDeleteTable(final String tableName) {
    return ("DROP TABLE " + tableName + ";");
  }

  //method
  @Override
  public String createStatementToRenameColumn(
    final String tableName,
    final String columnName,
    final String newColumnName) {
    return ("ALTER TABLE " + tableName + " RENAME COLUMN " + columnName + " TO " + newColumnName + ";");
  }

  //method
  @Override
  public String createStatementToRenameTable(final String tableName, final String newTableName) {
    return ("ALTER TABLE " + tableName + " RENAME TO " + newTableName + ";");
  }

  //method
  private String getColumnAsSql(final IColumnDto column) {

    var sql = column.getName() + " " + getDataTypeAsSql(column.getDataType());

    if (column.getConstraints().containsAny()) {
      sql += getConstraintsAsSql(column);
    }

    return sql;
  }

  //method
  private String getConstraintAsSql(final IConstraintDto constraint) {

    var sql = constraint.getType().toString().replace(StringCatalogue.UNDERSCORE, StringCatalogue.SPACE);

    if (constraint.getParameters().containsAny()) {
      getConstraintParametersAsSql(constraint);
    }

    return sql;
  }

  //method
  private String getConstraintsAsSql(final IColumnDto column) {
    return column.getConstraints().to(this::getConstraintAsSql).toStringWithSeparator(",");
  }

  //method
  private String getConstraintParametersAsSql(final IConstraintDto constraint) {
    return ("(" + constraint.getParameters().toStringWithSeparator(",") + ")");
  }

  //method
  private String getDataTypeAsSql(final IDataTypeDto dataType) {

    if (!dataType.hasParameter()) {
      return dataType.getName();
    }

    return (dataType.getName() + "(" + dataType.getParameter() + ")");
  }
}
