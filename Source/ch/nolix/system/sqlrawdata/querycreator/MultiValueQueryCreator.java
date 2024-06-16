//package declaration
package ch.nolix.system.sqlrawdata.querycreator;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.sqlrawschema.multivalueentrytable.MultiValueEntryTableColumn;
import ch.nolix.system.sqlrawschema.structure.MultiEntryTableType;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IMultiValueQueryCreator;

//class
public final class MultiValueQueryCreator implements IMultiValueQueryCreator {

  //method
  @Override
  public String createQueryToCountMultiValueEntriesForGivenColumnAndValueIgnoringGivenEntities(
    final String columnId,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {
    return //
    "SELECT COUNT(*) FROM "
    + MultiEntryTableType.MULTI_VALUE_ENTRY.getQualifyingPrefix()
    + " WHERE "
    + MultiValueEntryTableColumn.MULTI_VALUE_COLUMN_ID.getQualifiedName()
    + " = '"
    + value
    + "' AND "
    + MultiValueEntryTableColumn.ENTITY_ID.getName()
    + " NOT IN ("
    + entitiesToIgnoreIds.toString()
    + ");";
  }

  //method
  @Override
  public String createQueryToLoadMultiValueEntries(final String entityId, final String multiValueColumnId) {
    return //
    "SELECT "
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
    return //
    "SELECT TOP 1 * FROM "
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
