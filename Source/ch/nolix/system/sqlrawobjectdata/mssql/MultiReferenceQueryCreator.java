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
		final String entityId,
		final String multiReferenceColumnId
	) {
		return
		"SELECT "
		+ MultiReferenceEntryTableColumn.REFERENCED_ENTITY_ID.getName()
		+ " FROM "
		+ MultiContentTable.MULTI_REFERENCE_ENTRY.getFullName()
		+ " WHERE "
		+ MultiReferenceEntryTableColumn.ENTITY_ID.getName()
		+ " = '"
		+ entityId
		+ "' AND "
		+ MultiReferenceEntryTableColumn.MULTI_REFERENCE_COLUMN_ID.getName()
		+ " = '"
		+ multiReferenceColumnId
		+ "'";
	}
}
