package ch.nolix.system.sqlrawdata.sqlmapper;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.coreapi.sqlapi.syntaxapi.SqlKeywordCatalog;
import ch.nolix.systemapi.rawdataapi.modelapi.StringContentFieldDto;
import ch.nolix.systemapi.sqlrawdataapi.sqlmapperapi.ISqlValueMapper;

/**
 * @author Silvan Wyss
 * @version 2024-12-28
 */
public class SqlValueMapper implements ISqlValueMapper {

  /**
   * {@inheritDoc}
   */
  @Override
  public String mapStringContentFieldDtoToSqlValue(final StringContentFieldDto stringContentFieldDto) {

    final var optionalContentString = stringContentFieldDto.optionalContentString();

    if (optionalContentString.isEmpty()) {
      return SqlKeywordCatalog.NULL;
    }

    return StringTool.getInSingleQuotes(optionalContentString);
  }
}
