/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmiddata.querycreator;

import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.systemapi.sqlmiddata.querycreator.IMultiReferenceQueryCreator;
import ch.nolix.systemapi.sqlmiddatabasestructure.column.MultiReferenceEntryColumn;
import ch.nolix.systemapi.sqlmiddatabasestructure.table.DataTable;

/**
 * @author Silvan Wyss
 */
public final class MultiReferenceQueryCreator implements IMultiReferenceQueryCreator {
  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToCountMultiReferenceEntriesForGivenColumnAndReferencedEntityIgnoringGivenEntities(
    final String columnId,
    final String referencedEntityId,
    final IContainer<String> entitiesToIgnoreIds) {
    return //
    "SELECT COUNT(*) FROM "
    + DataTable.MULTI_REFERENCE_ENTRY
    + " WHERE "
    + MultiReferenceEntryColumn.MULTI_REFERENCE_COLUMN_ID
    + " = '"
    + columnId
    + "' AND "
    + MultiReferenceEntryColumn.REFERENCED_ENTITY_ID
    + " = '"
    + referencedEntityId
    + "' AND "
    + MultiReferenceEntryColumn.ENTITY_ID
    + " NOT IN ("
    + entitiesToIgnoreIds.toString()
    + ");";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToLoadMultiReferenceEntries(
    final String entityId,
    final String multiReferenceColumnId) {
    return //
    "SELECT "
    + MultiReferenceEntryColumn.ENTITY_ID
    + ", "
    + MultiReferenceEntryColumn.ENTITY_TABLE_ID
    + ", "
    + MultiReferenceEntryColumn.MULTI_REFERENCE_COLUMN_ID
    + ", "
    + MultiReferenceEntryColumn.REFERENCED_ENTITY_ID
    + ", "
    + MultiReferenceEntryColumn.REFERENCED_ENTITY_TABLE_ID
    + " FROM "
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
  public String createQueryToLoadOptionalFirstMultiReferenceEntry(
    final String multiReferenceColumnId,
    final String referencedEntityId) {
    return //
    "SELECT TOP 1 * FROM "
    + DataTable.MULTI_REFERENCE_ENTRY
    + " WHERE "
    + MultiReferenceEntryColumn.MULTI_REFERENCE_COLUMN_ID
    + " = '"
    + multiReferenceColumnId
    + "' AND "
    + MultiReferenceEntryColumn.REFERENCED_ENTITY_ID
    + " = '"
    + referencedEntityId
    + "';";
  }
}
