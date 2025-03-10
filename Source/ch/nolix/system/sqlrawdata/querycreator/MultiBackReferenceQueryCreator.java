package ch.nolix.system.sqlrawdata.querycreator;

import ch.nolix.coreapi.sqlapi.syntaxapi.SpaceEnclosedSqlKeywordCatalog;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IMultiBackReferenceQueryCreator;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.FixTableType;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.MultiBackReferenceEntryTableColumn;

public final class MultiBackReferenceQueryCreator implements IMultiBackReferenceQueryCreator {

  @Override
  public String createQueryToLoadMultiBackReferenceEntries(
    final String entityId,
    final String multiBackReferenceColumnId) {
    return //
    "SELECT "
    + MultiBackReferenceEntryTableColumn.BACK_REFERENCED_ENTITY_ID.getName()
    + SpaceEnclosedSqlKeywordCatalog.FROM
    + FixTableType.MULTI_BACK_REFERENCE_ENTRY.getName()
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
    + FixTableType.MULTI_BACK_REFERENCE_ENTRY.getName()
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
