/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmiddata.statementcreator;

import ch.nolix.systemapi.sqlmiddata.statementcreator.IMultiReferenceStatementCreator;
import ch.nolix.systemapi.sqlmiddatabasestructure.column.MultiReferenceEntryColumn;
import ch.nolix.systemapi.sqlmiddatabasestructure.table.DataTable;

/**
 * @author Silvan Wyss
 */
public final class MultiReferenceStatementCreator implements IMultiReferenceStatementCreator {
  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToDeleteMultiReferenceEntries(
    final String entityId,
    final String multiReferenceColumnId) {
    return //
    "DELETE FROM "
    + DataTable.MULTI_REFERENCE_ENTRY
    + " WHERE "
    + MultiReferenceEntryColumn.ENTITY_ID
    + " = '"
    + entityId
    + "' AND "
    + MultiReferenceEntryColumn.MULTI_REFERENCE_COLUMN_ID
    + " = '"
    + multiReferenceColumnId
    + "';";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToDeleteMultiReferenceEntry(
    final String entityId,
    final String multiReferenceColumnId,
    final String referencedEntityId) {
    return //
    "DELETE FROM "
    + DataTable.MULTI_REFERENCE_ENTRY
    + " WHERE "
    + MultiReferenceEntryColumn.ENTITY_ID
    + " = '"
    + entityId
    + "' AND "
    + MultiReferenceEntryColumn.MULTI_REFERENCE_COLUMN_ID
    + " = '"
    + multiReferenceColumnId
    + "' AND "
    + MultiReferenceEntryColumn.REFERENCED_ENTITY_ID
    + " = '"
    + referencedEntityId
    + "';";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createStatementToInsertMultiReferenceEntry(
    final String entityId,
    final String multiReferenceColumnId,
    final String referencedEntityId,
    final String referencedEntityTableId) {
    return //
    "INSERT INTO "
    + DataTable.MULTI_REFERENCE_ENTRY
    + " ("
    + MultiReferenceEntryColumn.ENTITY_ID
    + ", "
    + MultiReferenceEntryColumn.MULTI_REFERENCE_COLUMN_ID
    + ", "
    + MultiReferenceEntryColumn.REFERENCED_ENTITY_ID
    + ", "
    + MultiReferenceEntryColumn.REFERENCED_ENTITY_TABLE_ID
    + ") VALUES ('"
    + entityId
    + "', '"
    + multiReferenceColumnId
    + "', '"
    + referencedEntityId
    + "');";
  }
}
