package ch.nolix.system.sqlmiddata.sqlmapper;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.coreapi.sqlapi.syntaxapi.SqlKeywordCatalog;
import ch.nolix.systemapi.middataapi.modelapi.StringValueFieldDto;
import ch.nolix.systemapi.sqlmiddataapi.sqlmapperapi.ISqlValueMapper;

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
