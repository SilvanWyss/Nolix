package ch.nolix.system.sqlmiddata.statementcreator;

import ch.nolix.systemapi.sqlmiddataapi.statementcreatorapi.IMultiBackReferenceStatementCreator;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.FixTable;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.MultiBackReferenceEntryColumn;

public final class MultiBackReferenceStatementCreator implements IMultiBackReferenceStatementCreator {

  @Override
  public String createStatementToDeleteMultiBackReferenceEntries(
    final String entityId,
    final String multiBackReferenceColumnId) {
    return "DELETE FROM "
    + FixTable.MULTI_BACK_REFERENCE_ENTRY.getName()
    + " WHERE "
    + MultiBackReferenceEntryColumn.ENTITY_ID.getName()
    + " = '"
    + entityId
    + "' AND "
    + MultiBackReferenceEntryColumn.MULTI_BACK_REFERENCE_COLUMN_ID.getName()
    + " = '"
    + multiBackReferenceColumnId
    + "';";
  }

  @Override
  public String createStatementToDeleteMultiBackReferenceEntry(
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {
    return "DELETE FROM "
    + FixTable.MULTI_BACK_REFERENCE_ENTRY.getName()
    + " WHERE "
    + MultiBackReferenceEntryColumn.ENTITY_ID.getName()
    + " = '"
    + entityId
    + "' AND "
    + MultiBackReferenceEntryColumn.MULTI_BACK_REFERENCE_COLUMN_ID.getName()
    + " = '"
    + multiBackReferenceColumnId
    + "' AND "
    + MultiBackReferenceEntryColumn.BACK_REFERENCED_ENTITY_ID.getName()
    + " = '"
    + backReferencedEntityId
    + "'";
  }

  @Override
  public String createStatementToInsertMultiBackReferenceEntry(
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId,
    final String backReferencedEntityTableId) {
    return "INSERT INTO "
    + FixTable.MULTI_BACK_REFERENCE_ENTRY.getName()
    + " ("
    + MultiBackReferenceEntryColumn.ENTITY_ID.getName()
    + ", "
    + MultiBackReferenceEntryColumn.MULTI_BACK_REFERENCE_COLUMN_ID.getName()
    + ", "
    + MultiBackReferenceEntryColumn.BACK_REFERENCED_ENTITY_ID.getName()
    + ", "
    + MultiBackReferenceEntryColumn.BACK_REFERENCED_ENTITY_TABLE_ID.getName()
    + ") VALUES ('"
    + entityId
    + "', '"
    + multiBackReferenceColumnId
    + "', '"
    + backReferencedEntityId
    + "', '"
    + backReferencedEntityTableId
    + "');";
  }
}
