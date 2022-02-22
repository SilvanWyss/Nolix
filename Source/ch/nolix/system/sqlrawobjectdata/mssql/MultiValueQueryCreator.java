//package declaration
package ch.nolix.system.sqlrawobjectdata.mssql;

//own imports
import ch.nolix.system.sqlrawobjectdata.sqlapi.IMultiValueQueryCreator;
import ch.nolix.system.sqlrawobjectschema.multivalueentrytable.MultiValueEntryTableColumn;
import ch.nolix.system.sqlrawobjectschema.structure.MultiContentTable;

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
		+ MultiValueEntryTableColumn.RECORD_ID.getName()
		+ " = '"
		+ entityId
		+ "' AND "
		+ MultiValueEntryTableColumn.MULTI_VALUE_COLUMN_ID.getName()
		+ " = '"
		+ multiValueColumnId
		+ "'";
	}
}
