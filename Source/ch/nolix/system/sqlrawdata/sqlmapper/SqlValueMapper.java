package ch.nolix.system.sqlrawdata.sqlmapper;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.coreapi.commontypetoolapi.stringtoolapi.IStringTool;
import ch.nolix.coreapi.sqlapi.syntaxapi.SqlKeywordCatalog;
import ch.nolix.systemapi.rawdataapi.modelapi.StringContentFieldDto;
import ch.nolix.systemapi.sqlrawdataapi.sqlmapperapi.ISqlValueMapper;

/**
 * @author Silvan Wyss
 * @version 2024-12-28
 */
public class SqlValueMapper implements ISqlValueMapper {

  private static final IStringTool STRING_TOOL = new StringTool();

  /**
   * {@inheritDoc}
   */
  @Override
  public String mapStringContentFieldDtoToSqlValue(final StringContentFieldDto stringContentFieldDto) {

    final var optionalContent = stringContentFieldDto.optionalContent();

    if (optionalContent.isEmpty()) {
      return SqlKeywordCatalog.NULL;
    }

    final var content = optionalContent.get();

    return STRING_TOOL.getInSingleQuotes(content);
  }
}
