//package declaration
package ch.nolix.system.sqldatabaserawdata.sqlsyntax;

import ch.nolix.system.sqldatabaserawschema.multivalueentrytable.MultiValueEntryTableColumn;
import ch.nolix.system.sqldatabaserawschema.structure.MultiContentTable;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.IMultiValueStatementCreator;

//class
public final class MultiValueStatementCreator implements IMultiValueStatementCreator {

  //method
  @Override
  public String createStatementToDeleteMultiValueEntries(final String entityId, final String multiValueColumnId) {
    return "DELETE FROM "
        + MultiContentTable.MULTI_VALUE_ENTRY.getQualifiedName()
        + " WHERE "
        + MultiValueEntryTableColumn.ENTITY_ID.getName()
        + " = '"
        + entityId
        + "' AND "
        + MultiValueEntryTableColumn.MULTI_VALUE_COLUMN_ID.getName()
        + " = '"
        + multiValueColumnId
        + "'";
  }

  //method
  @Override
  public String createStatementToDeleteMultiValueEntry(
      final String entityId,
      final String multiValueColumnId,
      final String entry) {
    return "DELETE FROM "
        + MultiContentTable.MULTI_VALUE_ENTRY.getQualifiedName()
        + " WHERE "
        + MultiValueEntryTableColumn.ENTITY_ID.getName()
        + " = '"
        + entityId
        + "' AND "
        + MultiValueEntryTableColumn.MULTI_VALUE_COLUMN_ID.getName()
        + " = '"
        + multiValueColumnId
        + "' AND "
        + MultiValueEntryTableColumn.VALUE.getName()
        + " = '"
        + entry
        + "'";
  }

  //method
  @Override
  public String createQueryToInsertEntryIntoMultiValue(
      final String entityId,
      final String multiValueColumnId,
      final String entry) {
    return "INSERT INTO "
        + MultiContentTable.MULTI_VALUE_ENTRY.getQualifiedName()
        + " ("
        + MultiValueEntryTableColumn.ENTITY_ID.getName()
        + ", "
        + MultiValueEntryTableColumn.MULTI_VALUE_COLUMN_ID.getName()
        + ", "
        + MultiValueEntryTableColumn.VALUE.getName()
        + ") VALUES ('"
        + entityId
        + "', '"
        + multiValueColumnId
        + "', '"
        + entry
        + "')";
  }
}
