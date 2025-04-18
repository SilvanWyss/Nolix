package ch.nolix.system.sqlmiddata.statementcreator;

import ch.nolix.systemapi.sqlmiddataapi.statementcreatorapi.IMultiReferenceStatementCreator;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.FixTable;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.MultiReferenceEntryColumn;

public final class MultiReferenceStatementCreator implements IMultiReferenceStatementCreator {

  @Override
  public String createStatementToDeleteMultiReferenceEntries(
    final String entityId,
    final String multiReferenceColumnId) {
    return //
    "DELETE FROM "
    + FixTable.MULTI_REFERENCE_ENTRY.getName()
    + " WHERE "
    + MultiReferenceEntryColumn.ENTITY_ID.getName()
    + " = '"
    + entityId
    + "' AND "
    + MultiReferenceEntryColumn.MULTI_REFERENCE_COLUMN_ID.getName()
    + " = '"
    + multiReferenceColumnId
    + "';";
  }

  @Override
  public String createStatementToDeleteMultiReferenceEntry(
    final String entityId,
    final String multiReferenceColumnId,
    final String referencedEntityId) {
    return //
    "DELETE FROM "
    + FixTable.MULTI_REFERENCE_ENTRY.getName()
    + " WHERE "
    + MultiReferenceEntryColumn.ENTITY_ID.getName()
    + " = '"
    + entityId
    + "' AND "
    + MultiReferenceEntryColumn.MULTI_REFERENCE_COLUMN_ID.getName()
    + " = '"
    + multiReferenceColumnId
    + "' AND "
    + MultiReferenceEntryColumn.REFERENCED_ENTITY_ID.getName()
    + " = '"
    + referencedEntityId
    + "';";
  }

  @Override
  public String createStatementToInsertMultiReferenceEntry(
    final String entityId,
    final String multiReferenceColumnId,
    final String referencedEntityId,
    final String referencedEntityTableId) {
    return //
    "INSERT INTO "
    + FixTable.MULTI_REFERENCE_ENTRY.getName()
    + " ("
    + MultiReferenceEntryColumn.ENTITY_ID.getName()
    + ", "
    + MultiReferenceEntryColumn.MULTI_REFERENCE_COLUMN_ID.getName()
    + ", "
    + MultiReferenceEntryColumn.REFERENCED_ENTITY_ID.getName()
    + ", "
    + MultiReferenceEntryColumn.REFERENCED_ENTITY_TABLE_ID.getName()
    + ") VALUES ('"
    + entityId
    + "', '"
    + multiReferenceColumnId
    + "', '"
    + referencedEntityId
    + "');";
  }
}
