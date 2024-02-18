//package declaration
package ch.nolix.system.sqlrawdata.statementcreator;

//own imports
import ch.nolix.system.sqlrawschema.multivalueentrytable.MultiValueEntryTableColumn;
import ch.nolix.system.sqlrawschema.structure.MultiEntryTableType;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IMultiValueStatementCreator;

//class
public final class MultiValueStatementCreator implements IMultiValueStatementCreator {

  //method
  @Override
  public String createStatementToDeleteMultiValueEntries(final String entityId, final String multiValueColumnId) {
    return "DELETE FROM "
    + MultiEntryTableType.MULTI_VALUE_ENTRY.getQualifiedName()
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
    + MultiEntryTableType.MULTI_VALUE_ENTRY.getQualifiedName()
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
  public String createStatementToInsertMultiValueEntry(
    final String entityId,
    final String multiValueColumnId,
    final String entry) {
    return "INSERT INTO "
    + MultiEntryTableType.MULTI_VALUE_ENTRY.getQualifiedName()
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