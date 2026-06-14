/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmiddata.sqlmapper;

import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.base.sql.sqltool.SqlLiteralMapper;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.sql.sqltool.ISqlLiteralMapper;
import ch.nolix.systemapi.middata.model.ValueStringFieldDto;
import ch.nolix.systemapi.sqlmiddata.sqlmapper.ISqlPartsMapper;

/**
 * @author Silvan Wyss
 */
public final class SqlPartsMapper implements ISqlPartsMapper {
  private static final ISqlLiteralMapper SQL_LITERAL_MAPPER = new SqlLiteralMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public IWellOrderContainer<String> mapValueStringFieldDtoToColumnNames(final ValueStringFieldDto valueStringFieldDto) {
    final var columnName = valueStringFieldDto.columnName();

    if (valueStringFieldDto.nullableAdditionalValue() != null) {
      final var additionalColumnName = columnName + "Table";

      return ImmutableList.withElements(columnName, additionalColumnName);
    }

    return ImmutableList.withElements(columnName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IWellOrderContainer<String> mapValueStringFieldDtoToSqlValueLiterals(final ValueStringFieldDto valueStringFieldDto) {
    final var nullableValueString = valueStringFieldDto.nullableValueString();
    final var valueSqlLiteral = SQL_LITERAL_MAPPER.mapNullableValueStringToSqlLiteral(nullableValueString);
    final var nullableAdditionalValue = valueStringFieldDto.nullableAdditionalValue();

    if (nullableAdditionalValue != null) {
      final var additionalValueSqlLiteral = //
      SQL_LITERAL_MAPPER.mapNullableValueStringToSqlLiteral(nullableAdditionalValue);

      return ImmutableList.withElements(valueSqlLiteral, additionalValueSqlLiteral);
    }

    return ImmutableList.withElements(valueSqlLiteral);
  }
}
