package ch.nolix.core.sql.sqltool;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.coreapi.sql.sqltool.ISqlLiteralMapper;
import ch.nolix.coreapi.sql.syntax.SqlKeywordCatalog;

/**
 * @author Silvan Wyss
 */
public class SqlLiteralMapper implements ISqlLiteralMapper {
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
