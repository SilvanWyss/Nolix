//package declaration
package ch.nolix.system.sqldatabaserawdata.sqlsyntax;

import ch.nolix.system.sqldatabaserawschema.multireferenceentrytable.MultiReferenceEntryTableColumn;
import ch.nolix.system.sqldatabaserawschema.structure.MultiEntryTableType;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.IMultiReferenceStatementCreator;

//class
public final class MultiReferenceStatementCreator implements IMultiReferenceStatementCreator {

  //method
  @Override
  public String createStatementToDeleteMultiReferenceEntries(
    final String entityId,
    final String multiReferenceColumnId) {
    return "DELETE FROM "
    + MultiEntryTableType.MULTI_REFERENCE_ENTRY.getQualifiedName()
    + " WHERE "
    + MultiReferenceEntryTableColumn.ENTITY_ID.getName()
    + " = '"
    + entityId
    + "' AND "
    + MultiReferenceEntryTableColumn.MULTI_REFERENCE_COLUMN_ID.getName()
    + " = '"
    + multiReferenceColumnId
    + "'";
  }

  //method
  @Override
  public String createStatementToDeleteMultiReferenceEntry(
    final String entityId,
    final String multiReferenceColumnId,
    final String referencedEntityId) {
    return "DELETE FROM "
    + MultiEntryTableType.MULTI_REFERENCE_ENTRY.getQualifiedName()
    + " WHERE "
    + MultiReferenceEntryTableColumn.ENTITY_ID.getName()
    + " = '"
    + entityId
    + "' AND "
    + MultiReferenceEntryTableColumn.MULTI_REFERENCE_COLUMN_ID.getName()
    + " = '"
    + multiReferenceColumnId
    + "' AND "
    + MultiReferenceEntryTableColumn.REFERENCED_ENTITY_ID.getName()
    + " = '"
    + multiReferenceColumnId
    + "'";
  }

  //method
  @Override
  public String createStatementToInsertEntryIntoMultiReference(
    final String entityId,
    final String multiReferenceColumnId,
    final String referencedEntityId) {
    return "INSERT INTO "
    + MultiEntryTableType.MULTI_REFERENCE_ENTRY.getQualifiedName()
    + " ("
    + MultiReferenceEntryTableColumn.ENTITY_ID.getName()
    + ", "
    + MultiReferenceEntryTableColumn.MULTI_REFERENCE_COLUMN_ID.getName()
    + ", "
    + MultiReferenceEntryTableColumn.REFERENCED_ENTITY_ID.getName()
    + ") VALUES ('"
    + entityId
    + "', '"
    + multiReferenceColumnId
    + "', '"
    + referencedEntityId
    + "')";
  }
}
