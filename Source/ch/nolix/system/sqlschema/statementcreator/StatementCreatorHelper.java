/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlschema.statementcreator;

import ch.nolix.coreapi.commontypetool.stringtool.StringCatalog;
import ch.nolix.systemapi.sqlschema.model.ColumnConstraintDto;
import ch.nolix.systemapi.sqlschema.model.ColumnDto;
import ch.nolix.systemapi.sqlschema.model.DataTypeDto;

/**
 * @author Silvan Wyss
 */
public final class StatementCreatorHelper {
  private StatementCreatorHelper() {
  }

  public static String getColumnAsSql(final ColumnDto column) {
    var sql = column.name() + " " + getDataTypeAsSql(column.dataType());

    if (column.constraints().containsAny()) {
      sql += getConstraintsAsSql(column);
    }

    return sql;
  }

  private static String getConstraintAsSql(final ColumnConstraintDto constraint) {
    var sql = constraint.constraint().toString().replace(StringCatalog.UNDERSCORE, StringCatalog.SPACE);

    if (constraint.parameters().containsAny()) {
      getConstraintParametersAsSql(constraint);
    }

    return sql;
  }

  private static String getConstraintsAsSql(final ColumnDto column) {
    return column.constraints().getViewOf(StatementCreatorHelper::getConstraintAsSql).toStringWithSeparator(",");
  }

  private static String getConstraintParametersAsSql(final ColumnConstraintDto constraint) {
    return ("(" + constraint.parameters().toStringWithSeparator(",") + ")");
  }

  private static String getDataTypeAsSql(final DataTypeDto dataType) {
    final var parameter = dataType.nullableParameter();

    if (parameter != null) {
      return (dataType.name() + "(" + parameter + ")");
    }

    return dataType.name();
  }
}
