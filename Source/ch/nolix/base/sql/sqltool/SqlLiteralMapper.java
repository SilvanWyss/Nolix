/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.sql.sqltool;

import ch.nolix.base.commontype.stringtool.StringTool;
import ch.nolix.baseapi.sql.sqltool.ISqlLiteralMapper;
import ch.nolix.baseapi.sql.syntax.SqlKeywordCatalog;

/**
 * @author Silvan Wyss
 */
public final class SqlLiteralMapper implements ISqlLiteralMapper {
  /**
   * {@inheritDoc}
   */
  @Override
  public String mapNullableValueStringToSqlLiteral(final String nullableValueString) {
    if (nullableValueString == null) {
      return SqlKeywordCatalog.NULL;
    }

    return StringTool.getInSingleQuotes(nullableValueString);
  }
}
