package ch.nolix.system.sqlmiddata.querycreator;

import ch.nolix.coreapi.sqlapi.syntaxapi.SpaceEnclosedSqlKeywordCatalog;
import ch.nolix.systemapi.sqlmiddataapi.querycreatorapi.IMultiBackReferenceQueryCreator;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.FixTable;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.MultiBackReferenceEntryTableColumn;

public final class MultiBackReferenceQueryCreator implements IMultiBackReferenceQueryCreator {

  @Override
  public String createQueryToLoadMultiBackReferenceEntries(
    final String entityId,
    final String multiBackReferenceColumnId) {
    return //
    "SELECT "
    + MultiBackReferenceEntryTableColumn.BACK_REFERENCED_ENTITY_ID.getName()
    + SpaceEnclosedSqlKeywordCatalog.FROM
    + FixTable.MULTI_BACK_REFERENCE_ENTRY.getName()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
    + MultiBackReferenceEntryTableColumn.ENTITY_ID.getName()
    + " = '"
    + entityId
    + "' AND "
    + MultiBackReferenceEntryTableColumn.MULTI_BACK_REFERENCE_COLUMN_ID.getName()
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
    + SpaceEnclosedSqlKeywordCatalog.WHERE
    + MultiBackReferenceEntryTableColumn.MULTI_BACK_REFERENCE_COLUMN_ID.getName()
    + " = '"
    + multiBackReferenceColumnId
    + "' AND "
    + MultiBackReferenceEntryTableColumn.BACK_REFERENCED_ENTITY_ID.getName()
    + " = '"
    + backReferencedEntityId
    + "';";
  }
}
