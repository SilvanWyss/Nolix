//package declaration
package ch.nolix.system.sqlrawdata.mssql;

//own imports
import ch.nolix.system.sqlrawdata.sqlapi.IMultiValueStatementCreator;
import ch.nolix.system.sqlrawschema.multivalueentrytable.MultiValueEntryTableColumn;
import ch.nolix.system.sqlrawschema.structure.MultiContentTable;

//class
public final class MultiValueStatementCreator implements IMultiValueStatementCreator{
	
	//method
	@Override
	public String createStatementToDeleteEntriesFromMultiValue(final String entityId, final String multiValueColumnId) {
		return
		"DELETE FROM "
		+  MultiContentTable.MULTI_VALUE_ENTRY.getFullName()
		+ " WHERE "
		+ MultiValueEntryTableColumn.ENTITY_ID.getName()
		+ " = '"
		+ entityId
		+ "' AND "
		+ MultiValueEntryTableColumn.MULTI_VALUE_COLUMN_ID.getName()
		+ " = '"
		+ multiValueColumnId
		+ "'";
	}
	
	//method
	@Override
	public String createStatementToDeleteEntryFromMultiValue(
		final String entityId,
		final String multiValueColumnId,
		final String entry
	) {
		return
		"DELETE FROM "
		+  MultiContentTable.MULTI_VALUE_ENTRY.getFullName()
		+ " WHERE "
		+ MultiValueEntryTableColumn.ENTITY_ID.getName()
		+ " = '"
		+ entityId
		+ "' AND "
		+ MultiValueEntryTableColumn.MULTI_VALUE_COLUMN_ID.getName()
		+ " = '"
		+ multiValueColumnId
		+ "' AND "
		+ MultiValueEntryTableColumn.VALUE.getName()
		+ " = '"
		+ entry
		+ "'";
	}
	
	//method
	@Override
	public String createQueryToInsertEntryIntoMultiValue(
		final String entityId,
		final String multiValueColumnId,
		final String entry
	) {
		return
		"INSERT INTO "
		+ MultiContentTable.MULTI_VALUE_ENTRY.getFullName()
		+ " ("
		+ MultiValueEntryTableColumn.ENTITY_ID.getName()
		+ ", "
		+ MultiValueEntryTableColumn.MULTI_VALUE_COLUMN_ID.getName()
		+ ", "
		+ MultiValueEntryTableColumn.VALUE.getName()
		+ ") VALUES ('"
		+ entityId
		+ "', '"
		+ multiValueColumnId
		+ "', '"
		+ entry
		+ "')";
	}
}
