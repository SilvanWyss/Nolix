//package declaration
package ch.nolix.system.sqlrawdata.statementcreator;

//own imports
import ch.nolix.system.sqlrawschema.multibackreferenceentrytable.MultiBackReferenceEntryTableColumn;
import ch.nolix.system.sqlrawschema.structure.MultiEntryTableType;
import ch.nolix.systemapi.sqlrawdataapi.statementcreatorapi.IMultiBackReferenceStatementCreator;

//own imports
public final class MultiBackReferenceStatementCreator implements IMultiBackReferenceStatementCreator {

  //method
  @Override
  public String createStatementToDeleteMultiBackReferenceEntries(
    final String entityId,
    final String multiBackReferenceColumnId) {
    return "DELETE FROM "
    + MultiEntryTableType.MULTI_BACK_REFERENCE_ENTRY.getQualifiedName()
    + " WHERE "
    + MultiBackReferenceEntryTableColumn.ENTITY_ID.getName()
    + " = '"
    + entityId
    + "' AND "
    + MultiBackReferenceEntryTableColumn.MULTI_BACK_REFERENCE_COLUMN_ID.getName()
    + " = '"
    + multiBackReferenceColumnId
    + "';";
  }

  //method
  @Override
  public String createStatementToDeleteMultiBackReferenceEntry(
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {
    return "DELETE FROM "
    + MultiEntryTableType.MULTI_BACK_REFERENCE_ENTRY.getQualifiedName()
    + " WHERE "
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

  //method
  @Override
  public String createStatementToInsertMultiBackReferenceEntry(
    final String entityId,
    final String multiBackReferenceColumnId,
    final String backReferencedEntityId) {
    return "INSERT INTO "
    + MultiEntryTableType.MULTI_BACK_REFERENCE_ENTRY.getQualifiedName()
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
