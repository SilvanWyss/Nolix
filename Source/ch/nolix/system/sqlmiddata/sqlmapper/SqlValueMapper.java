package ch.nolix.system.sqlmiddata.sqlmapper;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.coreapi.sql.syntax.SqlKeywordCatalog;
import ch.nolix.systemapi.middata.model.StringValueFieldDto;
import ch.nolix.systemapi.sqlmiddata.sqlmapper.ISqlValueMapper;

/**
 * @author Silvan Wyss
 * @version 2024-12-28
 */
public class SqlValueMapper implements ISqlValueMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public String mapStringContentFieldDtoToSqlValue(final StringValueFieldDto stringValueFieldDto) {

    final var nullableValue = stringValueFieldDto.nullableValue();

    if (nullableValue.isEmpty()) {
      return SqlKeywordCatalog.NULL;
    }

    return StringTool.getInSingleQuotes(nullableValue);
  }
}
