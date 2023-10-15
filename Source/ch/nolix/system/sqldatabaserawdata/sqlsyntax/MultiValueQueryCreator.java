//package declaration
package ch.nolix.system.sqldatabaserawdata.sqlsyntax;

import ch.nolix.system.sqldatabaserawschema.multivalueentrytable.MultiValueEntryTableColumn;
import ch.nolix.system.sqldatabaserawschema.structure.MultiContentTable;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.IMultiValueQueryCreator;

//class
public final class MultiValueQueryCreator implements IMultiValueQueryCreator {

  // method
  @Override
  public String createQueryToLoadMultiValueEntries(String entityId, String multiValueColumnId) {
    return "SELECT "
        + MultiValueEntryTableColumn.VALUE.getName()
        + " FROM "
        + MultiContentTable.MULTI_VALUE_ENTRY.getQualifiedName()
        + " WHERE "
        + MultiValueEntryTableColumn.ENTITY_ID.getName()
        + " = '"
        + entityId
        + "' AND "
        + MultiValueEntryTableColumn.MULTI_VALUE_COLUMN_ID.getName()
        + " = '"
        + multiValueColumnId
        + "';";
  }

  // method
  @Override
  public String createQueryToLoadOneOrNoneMultiValueEntryForGivenColumnAndValue(
      final String columnId,
      final String value) {
    return "SELECT TOP 1 FROM "
        + MultiContentTable.MULTI_VALUE_ENTRY.getQualifiedName()
        + " WHERE "
        + MultiValueEntryTableColumn.MULTI_VALUE_COLUMN_ID.getName()
        + " = '"
        + columnId
        + "' AND "
        + MultiValueEntryTableColumn.VALUE.getName()
        + " = '"
        + value
        + "';";
  }
}
