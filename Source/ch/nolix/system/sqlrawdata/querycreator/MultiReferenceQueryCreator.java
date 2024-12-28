package ch.nolix.system.sqlrawdata.querycreator;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.sqlapi.syntaxapi.SpaceEnclosedSqlKeywordCatalogue;
import ch.nolix.system.sqlrawschema.multireferenceentrytable.MultiReferenceEntryTableColumn;
import ch.nolix.systemapi.sqlrawdataapi.databasestructure.MultiEntryTableType;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IMultiReferenceQueryCreator;

public final class MultiReferenceQueryCreator implements IMultiReferenceQueryCreator {

  @Override
  public String createQueryToCountMultiReferenceEntriesForGivenColumnAndReferencedEntityIgnoringGivenEntities(
    final String columnId,
    final String referencedEntityId,
    final IContainer<String> entitiesToIgnoreIds) {
    return //
    "SELECT COUNT(*) FROM "
    + MultiEntryTableType.MULTI_REFERENCE_ENTRY.getQualifyingPrefix()
    + SpaceEnclosedSqlKeywordCatalogue.WHERE
    + MultiReferenceEntryTableColumn.MULTI_REFERENCE_COLUMN_ID.getName()
    + " = '"
    + columnId
    + "' AND "
    + MultiReferenceEntryTableColumn.REFERENCED_ENTITY_ID.getName()
    + " = '"
    + referencedEntityId
    + "' AND "
    + MultiReferenceEntryTableColumn.ENTITY_ID.getName()
    + " NOT IN ("
    + entitiesToIgnoreIds.toString()
    + ");";
  }

  @Override
  public String createQueryToLoadMultiReferenceEntries(
    final String entityId,
    final String multiReferenceColumnId) {
    return "SELECT "
    + MultiReferenceEntryTableColumn.REFERENCED_ENTITY_ID.getName()
    + " FROM "
    + MultiEntryTableType.MULTI_REFERENCE_ENTRY.getQualifiedName()
    + SpaceEnclosedSqlKeywordCatalogue.WHERE
    + MultiReferenceEntryTableColumn.ENTITY_ID.getName()
    + " = '"
    + entityId
    + "' AND "
    + MultiReferenceEntryTableColumn.MULTI_REFERENCE_COLUMN_ID.getName()
    + " = '"
    + multiReferenceColumnId
    + "';";
  }

  @Override
  public String createQueryToLoadOptionalFirstMultiReferenceEntry(
    final String multiReferenceColumnId,
    final String referencedEntityId) {
    return "SELECT TOP 1 * FROM "
    + MultiEntryTableType.MULTI_REFERENCE_ENTRY.getQualifiedName()
    + SpaceEnclosedSqlKeywordCatalogue.WHERE
    + MultiReferenceEntryTableColumn.MULTI_REFERENCE_COLUMN_ID.getName()
    + " = '"
    + multiReferenceColumnId
    + "' AND "
    + MultiReferenceEntryTableColumn.REFERENCED_ENTITY_ID.getName()
    + " = '"
    + referencedEntityId
    + "';";
  }
}
