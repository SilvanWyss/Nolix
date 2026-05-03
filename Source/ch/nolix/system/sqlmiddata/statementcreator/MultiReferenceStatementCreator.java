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
    + MultiReferenceEntryColumn.ENTITY_ID.getName()
    + " = '"
    + entityId
    + "' AND "
    + MultiReferenceEntryColumn.MULTI_REFERENCE_COLUMN_ID.getName()
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
