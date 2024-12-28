package ch.nolix.system.sqlrawdata.statementcreator;

import ch.nolix.system.sqlrawschema.multireferenceentrytable.MultiReferenceEntryTableColumn;
import ch.nolix.systemapi.sqlrawdataapi.databasestructure.MultiEntryTableType;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IMultiReferenceStatementCreator;

public final class MultiReferenceStatementCreator implements IMultiReferenceStatementCreator {

  @Override
  public String createStatementToDeleteMultiReferenceEntries(
    final String entityId,
    final String multiReferenceColumnId) {
    return "DELETE FROM "
    + MultiEntryTableType.MULTI_REFERENCE_ENTRY.getQualifiedName()
    + " WHERE "
    + MultiReferenceEntryTableColumn.ENTITY_ID.getName()
    + " = '"
    + entityId
    + "' AND "
    + MultiReferenceEntryTableColumn.MULTI_REFERENCE_COLUMN_ID.getName()
    + " = '"
    + multiReferenceColumnId
    + "';";
  }

  @Override
  public String createStatementToDeleteMultiReferenceEntry(
    final String entityId,
    final String multiReferenceColumnId,
    final String referencedEntityId) {
    return "DELETE FROM "
    + MultiEntryTableType.MULTI_REFERENCE_ENTRY.getQualifiedName()
    + " WHERE "
    + MultiReferenceEntryTableColumn.ENTITY_ID.getName()
    + " = '"
    + entityId
    + "' AND "
    + MultiReferenceEntryTableColumn.MULTI_REFERENCE_COLUMN_ID.getName()
    + " = '"
    + multiReferenceColumnId
    + "' AND "
    + MultiReferenceEntryTableColumn.REFERENCED_ENTITY_ID.getName()
    + " = '"
    + referencedEntityId
    + "';";
  }

  @Override
  public String createStatementToInsertMultiReferenceEntry(
    final String entityId,
    final String multiReferenceColumnId,
    final String referencedEntityId) {
    return "INSERT INTO "
    + MultiEntryTableType.MULTI_REFERENCE_ENTRY.getQualifiedName()
    + " ("
    + MultiReferenceEntryTableColumn.ENTITY_ID.getName()
    + ", "
    + MultiReferenceEntryTableColumn.MULTI_REFERENCE_COLUMN_ID.getName()
    + ", "
    + MultiReferenceEntryTableColumn.REFERENCED_ENTITY_ID.getName()
    + ") VALUES ('"
    + entityId
    + "', '"
    + multiReferenceColumnId
    + "', '"
    + referencedEntityId
    + "');";
  }
}
