/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmiddata.statementcreator;

import ch.nolix.systemapi.sqlmiddata.statementcreator.IMultiBackReferenceStatementCreator;
import ch.nolix.systemapi.sqlmidschema.databasestructure.MultiBackReferenceEntryColumn;
import ch.nolix.systemapi.sqlmidschema.tablestructure.DataTable;

/**
 * @author Silvan Wyss
 */
public final class MultiBackReferenceStatementCreator implements IMultiBackReferenceStatementCreator {
  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToDeleteMultiBackReferenceEntries(
    final String entityId,
    final String multiBackReferenceColumnId) {
    return "DELETE FROM "
    + DataTable.MULTI_BACK_REFERENCE_ENTRY
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

  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToDeleteMultiBackReferenceEntry(
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {
    return "DELETE FROM "
    + DataTable.MULTI_BACK_REFERENCE_ENTRY
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

  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToInsertMultiBackReferenceEntry(
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId,
    final String backReferencedEntityTableId) {
    return "INSERT INTO "
    + DataTable.MULTI_BACK_REFERENCE_ENTRY
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
