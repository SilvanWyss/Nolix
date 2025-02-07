package ch.nolix.system.sqlrawdata.statementcreator;

import ch.nolix.coreapi.sqlapi.syntaxapi.SpaceEnclosedSqlKeywordCatalog;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IMultiBackReferenceStatementCreator;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.FixTableType;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.MultiBackReferenceEntryTableColumn;

public final class MultiBackReferenceStatementCreator implements IMultiBackReferenceStatementCreator {

  @Override
  public String createStatementToDeleteMultiBackReferenceEntries(
    final String entityId,
    final String multiBackReferenceColumnId) {
    return "DELETE FROM "
    + FixTableType.MULTI_BACK_REFERENCE_ENTRY.getQualifiedName()
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
  public String createStatementToDeleteMultiBackReferenceEntry(
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {
    return "DELETE FROM "
    + FixTableType.MULTI_BACK_REFERENCE_ENTRY.getQualifiedName()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
    + MultiBackReferenceEntryTableColumn.ENTITY_ID.getName()
    + " = '"
    + entityId
    + "' AND "
    + MultiBackReferenceEntryTableColumn.MULTI_BACK_REFERENCE_COLUMN_ID.getName()
    + " = '"
    + multiBackReferenceColumnId
    + "' AND "
    + MultiBackReferenceEntryTableColumn.BACK_REFERENCED_ENTITY_ID.getName()
    + " = '"
    + backReferencedEntityId
    + "'";
  }

  @Override
  public String createStatementToInsertMultiBackReferenceEntry(
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {
    return "INSERT INTO "
    + FixTableType.MULTI_BACK_REFERENCE_ENTRY.getQualifiedName()
    + " ("
    + MultiBackReferenceEntryTableColumn.ENTITY_ID.getName()
    + ", "
    + MultiBackReferenceEntryTableColumn.MULTI_BACK_REFERENCE_COLUMN_ID.getName()
    + ", "
    + MultiBackReferenceEntryTableColumn.BACK_REFERENCED_ENTITY_ID.getName()
    + ") VALUES ('"
    + entityId
    + "', '"
    + multiBackReferenceColumnId
    + "', '"
    + backReferencedEntityId
    + "');";
  }
}
