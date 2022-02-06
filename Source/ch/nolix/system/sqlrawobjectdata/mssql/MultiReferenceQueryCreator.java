//package declaration
package ch.nolix.system.sqlrawobjectdata.mssql;

//own imports
import ch.nolix.system.sqlrawobjectdata.sqlapi.IMultiReferenceQueryCreator;
import ch.nolix.system.sqlrawobjectschema.multireferenceentrytable.MultiReferenceEntryTableColumn;
import ch.nolix.system.sqlrawobjectschema.structure.MultiContentTable;

//class
public final class MultiReferenceQueryCreator implements IMultiReferenceQueryCreator {
	
	//method
	@Override
	public String createQueryToLoadAllMultiReferenceEntriesForRecord(
		final String recordId,
		final String multiReferenceColumnId
	) {
		return
		"SELECT "
		+ MultiReferenceEntryTableColumn.REFERENCED_RECORD_ID.getName()
		+ " FROM "
		+ MultiContentTable.MULTI_REFERENCE_ENTRY.getFullName()
		+ " WHERE "
		+ MultiReferenceEntryTableColumn.RECORD_ID.getName()
		+ " = '"
		+ recordId
		+ "' AND "
		+ MultiReferenceEntryTableColumn.MULTI_REFERENCE_COLUMN_ID.getName()
		+ " = '"
		+ multiReferenceColumnId
		+ "'";
	}
}
