package ch.nolix.system.sqlrawschema.sqlrecordmapper;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.coreapi.commontypetoolapi.stringtoolapi.IStringTool;
import ch.nolix.systemapi.rawschemaapi.dto.TableDto;
import ch.nolix.systemapi.sqlrawschemaapi.modelsqlrecord.TableTableSqlRecord;

/**
 * @author Silvan Wyss
 * @version 2025-01-05
 */
public final class TableTableSqlRecordMapper {

  private static final IStringTool STRING_TOOL = new StringTool();

  public TableTableSqlRecord mapTableDtoToTableTableSqlRecord(final TableDto table) {
    return //
    new TableTableSqlRecord(STRING_TOOL.getInSingleQuotes(table.id()), STRING_TOOL.getInSingleQuotes(table.name()));
  }
}
