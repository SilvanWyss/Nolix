//package declaration
package ch.nolix.system.sqlrawdata.querycreator;

//own imports
import ch.nolix.system.sqlrawschema.multivalueentrytable.MultiValueEntryTableColumn;
import ch.nolix.system.sqlrawschema.structure.MultiEntryTableType;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IMultiValueQueryCreator;

//class
public final class MultiValueQueryCreator implements IMultiValueQueryCreator {

  //method
  @Override
  public String createQueryToLoadMultiValueEntries(String entityId, String multiValueColumnId) {
    return "SELECT "
    + MultiValueEntryTableColumn.VALUE.getName()
    + " FROM "
    + MultiEntryTableType.MULTI_VALUE_ENTRY.getQualifiedName()
    + " WHERE "
    + MultiValueEntryTableColumn.ENTITY_ID.getName()
    + " = '"
    + entityId
    + "' AND "
    + MultiValueEntryTableColumn.MULTI_VALUE_COLUMN_ID.getName()
    + " = '"
    + multiValueColumnId
    + "';";
  }

  //method
  @Override
  public String createQueryToLoadOneOrNoneMultiValueEntryForGivenColumnAndValue(
    final String columnId,
    final String value) {
    return "SELECT TOP 1 * FROM "
    + MultiEntryTableType.MULTI_VALUE_ENTRY.getQualifiedName()
    + " WHERE "
    + MultiValueEntryTableColumn.MULTI_VALUE_COLUMN_ID.getName()
    + " = '"
    + columnId
    + "' AND "
    + MultiValueEntryTableColumn.VALUE.getName()
    + " = '"
    + value
    + "';";
  }
}
