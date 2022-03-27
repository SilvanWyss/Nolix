//package declaration
package ch.nolix.system.sqlrawdata.mssql;

//own imports
import ch.nolix.system.sqlrawdata.sqlapi.IMultiValueQueryCreator;
import ch.nolix.system.sqlrawschema.multivalueentrytable.MultiValueEntryTableColumn;
import ch.nolix.system.sqlrawschema.structure.MultiContentTable;

//class
public final class MultiValueQueryCreator implements IMultiValueQueryCreator {
	
	//method
	@Override
	public String createQueryToLoadMultiValueEntriesFromRecord(String entityId, String multiValueColumnId) {
		return
		"SELECT "
		+ MultiValueEntryTableColumn.VALUE.getName()
		+ " FROM "
		+ MultiContentTable.MULTI_VALUE_ENTRY.getFullName()
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
		final String value
	) {
		return
		"SELECT TOP 1 FROM "
		+ MultiContentTable.MULTI_VALUE_ENTRY.getFullName()
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
