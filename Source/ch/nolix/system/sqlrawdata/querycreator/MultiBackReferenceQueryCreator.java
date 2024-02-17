//package declaration
package ch.nolix.system.sqlrawdata.querycreator;

//own imports
import ch.nolix.system.sqlrawschema.multibackreferenceentrytable.MultiBackReferenceEntryTableColumn;
import ch.nolix.system.sqlrawschema.structure.MultiEntryTableType;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IMultiBackReferenceQueryCreator;

//class
public final class MultiBackReferenceQueryCreator implements IMultiBackReferenceQueryCreator {

  //method
  @Override
  public String createQueryToLoadMultiBackReferenceEntries(String entityId, String multiBackReferenceColumnId) {
    return "SELECT "
    + MultiBackReferenceEntryTableColumn.BACK_REFERENCED_ENTITY_ID.getName()
    + " FROM "
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
  public String createQueryToLoadOptionalFirstMultiBackReferenceEntry(
    String multiBackReferenceColumnId,
    String backReferencedEntityId) {
    return "SELECT TOP 1 * FROM "
    + MultiEntryTableType.MULTI_BACK_REFERENCE_ENTRY.getQualifiedName()
    + " WHERE "
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
