/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmiddata.statementcreator;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.sql.sqltool.SqlLiteralMapper;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.systemapi.middata.model.ValueStringFieldDto;
import ch.nolix.systemapi.sqlmiddata.statementcreator.ISqlValueAssignmentMapper;

/**
 * @author Silvan Wyss
 */
public final class SqlValueAssignmentMapper implements ISqlValueAssignmentMapper {
  private static final SqlLiteralMapper SQL_LITERAL_MAPPER = new SqlLiteralMapper();

  @Override
  public ExtendedIterable<String> mapValueStringFieldDtoToSqlValueAssignemnts(
    final ValueStringFieldDto valueStringFieldDto) {
    final var columnName = valueStringFieldDto.columnName();
    final var nullableValueString = valueStringFieldDto.nullableValueString();
    final var valueSqlLiteral = SQL_LITERAL_MAPPER.mapNullableValueStringToSqlLiteral(nullableValueString);
    final var nullableAdditionalValue = valueStringFieldDto.nullableAdditionalValue();

    if (nullableAdditionalValue != null) {
      final var additionalColumnName = columnName + "Table";

      final var additionalValueSqlLiteral = //
      SQL_LITERAL_MAPPER.mapNullableValueStringToSqlLiteral(nullableAdditionalValue);

      return //
      ImmutableList.withElements(
        columnName + " = " + valueSqlLiteral,
        additionalColumnName + " = " + additionalValueSqlLiteral);
    }

    return ImmutableList.withElements(columnName + " = " + valueSqlLiteral);
  }
}
