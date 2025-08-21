package ch.nolix.system.sqlmiddata.sqlmapper;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.coreapi.sql.syntax.SqlKeywordCatalog;
import ch.nolix.systemapi.middata.model.StringRepresentedFieldDto;
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
  public String mapStringContentFieldDtoToSqlValue(final StringRepresentedFieldDto stringRepresentedFieldDto) {

    final var nullableValue = stringRepresentedFieldDto.nullableStringRepresentedValue();

    if (nullableValue.isEmpty()) {
      return SqlKeywordCatalog.NULL;
    }

    return StringTool.getInSingleQuotes(nullableValue);
  }
}
