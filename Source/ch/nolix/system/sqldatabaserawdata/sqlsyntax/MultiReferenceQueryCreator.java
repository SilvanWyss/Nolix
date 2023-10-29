//package declaration
package ch.nolix.system.sqldatabaserawdata.sqlsyntax;

import ch.nolix.system.sqldatabaserawschema.multireferenceentrytable.MultiReferenceEntryTableColumn;
import ch.nolix.system.sqldatabaserawschema.structure.MultiContentTable;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.IMultiReferenceQueryCreator;

//class
public final class MultiReferenceQueryCreator implements IMultiReferenceQueryCreator {

  //method
  @Override
  public String createQueryToLoadMultiReferenceEntries(
    final String entityId,
    final String multiReferenceColumnId) {
    return "SELECT "
    + MultiReferenceEntryTableColumn.REFERENCED_ENTITY_ID.getName()
    + " FROM "
    + MultiContentTable.MULTI_REFERENCE_ENTRY.getQualifiedName()
    + " WHERE "
    + MultiReferenceEntryTableColumn.ENTITY_ID.getName()
    + " = '"
    + entityId
    + "' AND "
    + MultiReferenceEntryTableColumn.MULTI_REFERENCE_COLUMN_ID.getName()
    + " = '"
    + multiReferenceColumnId
    + "';";
  }

  //method
  @Override
  public String createQueryToLoadOneOrNoneMultiReferenceEntryForGivenColumnAndReferencedEntity(
    final String columnId,
    final String referencedEntityId) {
    return "SELECT TOP 1 * FROM "
    + MultiContentTable.MULTI_REFERENCE_ENTRY.getQualifiedName()
    + " WHERE "
    + MultiReferenceEntryTableColumn.MULTI_REFERENCE_COLUMN_ID.getName()
    + " = '"
    + columnId
    + "' AND "
    + MultiReferenceEntryTableColumn.REFERENCED_ENTITY_ID.getName()
    + " = '"
    + referencedEntityId
    + "';";
  }
}
