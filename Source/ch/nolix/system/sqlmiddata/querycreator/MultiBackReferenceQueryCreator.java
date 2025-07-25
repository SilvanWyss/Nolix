package ch.nolix.system.sqlmiddata.querycreator;

import ch.nolix.systemapi.sqlmiddata.querycreator.IMultiBackReferenceQueryCreator;
import ch.nolix.systemapi.sqlmidschema.databasestructure.FixTable;
import ch.nolix.systemapi.sqlmidschema.databasestructure.MultiBackReferenceEntryColumn;

public final class MultiBackReferenceQueryCreator implements IMultiBackReferenceQueryCreator {

  @Override
  public String createQueryToLoadMultiBackReferenceEntries(
    final String backReferencedTableName,
    final String backReferencedSingleReferenceColumnName,
    final String referencedEntityId) {
    return //
    "SELECT Id FROM "
    + backReferencedTableName
    + " WHERE "
    + backReferencedSingleReferenceColumnName
    + " = '"
    + referencedEntityId
    + "';";
  }

  @Override
  public String createQueryToLoadMultiBackReferenceEntries(
    final String entityId,
    final String multiBackReferenceColumnId) {
    return //
    "SELECT "
    + MultiBackReferenceEntryColumn.BACK_REFERENCED_ENTITY_ID.getName()
    + " FROM "
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
  public String createQueryToLoadOptionalFirstMultiBackReferenceEntry(
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {
    return //
    "SELECT TOP 1 * FROM "
    + FixTable.MULTI_BACK_REFERENCE_ENTRY.getName()
    + " WHERE "
    + MultiBackReferenceEntryColumn.MULTI_BACK_REFERENCE_COLUMN_ID.getName()
    + " = '"
    + multiBackReferenceColumnId
    + "' AND "
    + MultiBackReferenceEntryColumn.BACK_REFERENCED_ENTITY_ID.getName()
    + " = '"
    + backReferencedEntityId
    + "';";
  }
}
