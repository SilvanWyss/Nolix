/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmiddata.statementcreator;

import ch.nolix.systemapi.sqlmiddata.statementcreator.IMultiReferenceStatementCreator;
import ch.nolix.systemapi.sqlmidschema.databasestructure.FixTable;
import ch.nolix.systemapi.sqlmidschema.databasestructure.MultiReferenceEntryColumn;

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
