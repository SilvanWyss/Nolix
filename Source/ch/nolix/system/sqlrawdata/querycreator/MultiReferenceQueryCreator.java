//package declaration
package ch.nolix.system.sqlrawdata.querycreator;

//own imports
import ch.nolix.system.sqlrawschema.multireferenceentrytable.MultiReferenceEntryTableColumn;
import ch.nolix.system.sqlrawschema.structure.MultiEntryTableType;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IMultiReferenceQueryCreator;

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
    + MultiEntryTableType.MULTI_REFERENCE_ENTRY.getQualifiedName()
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
  public String createQueryToLoadOptionalMultiReferenceEntryForGivenColumnAndReferencedEntity(
    final String columnId,
    final String referencedEntityId) {
    return "SELECT TOP 1 * FROM "
    + MultiEntryTableType.MULTI_REFERENCE_ENTRY.getQualifiedName()
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
