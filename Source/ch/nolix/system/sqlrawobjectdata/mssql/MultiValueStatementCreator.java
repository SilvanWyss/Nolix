//package declaration
package ch.nolix.system.sqlrawobjectdata.mssql;

//own imports
import ch.nolix.system.sqlrawobjectdata.sqlapi.IMultiValueStatementCreator;
import ch.nolix.system.sqlrawobjectschema.multivalueentrytable.MultiValueEntryTableColumn;
import ch.nolix.system.sqlrawobjectschema.structure.MultiContentTable;

//class
public final class MultiValueStatementCreator implements IMultiValueStatementCreator{
	
	//method
	@Override
	public String createQueryToDeleteEntriesFromMultiValue(final String recordId, final String multiValueColumnId) {
		return
		"DELETE FROM "
		+  MultiContentTable.MULTI_VALUE_ENTRY.getFullName()
		+ " WHERE "
		+ MultiValueEntryTableColumn.RECORD_ID.getName()
		+ " = '"
		+ recordId
		+ "' AND "
		+ MultiValueEntryTableColumn.MULTI_VALUE_COLUMN_ID
		+ " = '"
		+ multiValueColumnId
		+ "'";
	}
	
	//method
	@Override
	public String createQueryToDeleteEntryFromMultiValue(
		final String recordId,
		final String multiValueColumnId,
		final String entry
	) {
		return
		"DELETE FROM "
		+  MultiContentTable.MULTI_VALUE_ENTRY.getFullName()
		+ " WHERE "
		+ MultiValueEntryTableColumn.RECORD_ID.getName()
		+ " = '"
		+ recordId
		+ "' AND "
		+ MultiValueEntryTableColumn.MULTI_VALUE_COLUMN_ID
		+ " = '"
		+ multiValueColumnId
		+ "' AND "
		+ MultiValueEntryTableColumn.VALUE
		+ " = '"
		+ entry
		+ "'";
	}
	
	//method
	@Override
	public String createQueryToInsertEntryIntoMultiValue(
		final String recordId,
		final String multiValueColumnId,
		final String entry
	) {
		return
		"INSERT INTO "
		+ MultiContentTable.MULTI_VALUE_ENTRY.getFullName()
		+ " ("
		+ MultiValueEntryTableColumn.RECORD_ID.getName()
		+ ", "
		+ MultiValueEntryTableColumn.MULTI_VALUE_COLUMN_ID.getName()
		+ ", "
		+ MultiValueEntryTableColumn.VALUE.getName()
		+ ") VALUES ('"
		+ recordId
		+ "', '"
		+ multiValueColumnId
		+ "', '"
		+ entry
		+ "')";
	}
}
