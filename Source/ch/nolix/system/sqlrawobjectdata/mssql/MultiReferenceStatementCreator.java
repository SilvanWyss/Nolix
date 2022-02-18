//package declaration
package ch.nolix.system.sqlrawobjectdata.mssql;

//own imports
import ch.nolix.system.sqlrawobjectdata.sqlapi.IMultiReferenceStatementCreator;
import ch.nolix.system.sqlrawobjectschema.multireferenceentrytable.MultiReferenceEntryTableColumn;
import ch.nolix.system.sqlrawobjectschema.structure.MultiContentTable;

//class
public final class MultiReferenceStatementCreator implements IMultiReferenceStatementCreator {
	
	//method
	@Override
	public String createStatementToDeleteEntriesFromMultiReference(
		final String entityId,
		final String multiReferenceColumnId
	) {
		return
		"DELETE FROM "
		+  MultiContentTable.MULTI_REFERENCE_ENTRY.getFullName()
		+ " WHERE "
		+ MultiReferenceEntryTableColumn.RECORD_ID.getName()
		+ " = '"
		+ entityId
		+ "' AND "
		+ MultiReferenceEntryTableColumn.MULTI_REFERENCE_COLUMN_ID.getName()
		+ " = '"
		+ multiReferenceColumnId
		+ "'";
	}
	
	//method
	@Override
	public String createStatementToDeleteEntryFromMultiReference(
		final String entityId,
		final String multiReferenceColumnId,
		final String referencedEntityId
	) {
		return
		"DELETE FROM "
		+  MultiContentTable.MULTI_REFERENCE_ENTRY.getFullName()
		+ " WHERE "
		+ MultiReferenceEntryTableColumn.RECORD_ID.getName()
		+ " = '"
		+ entityId
		+ "' AND "
		+ MultiReferenceEntryTableColumn.MULTI_REFERENCE_COLUMN_ID.getName()
		+ " = '"
		+ multiReferenceColumnId
		+ "' AND "
		+ MultiReferenceEntryTableColumn.REFERENCED_RECORD_ID.getName()
		+ " = '"
		+ multiReferenceColumnId
		+ "'";
	}
	
	//method
	@Override
	public String createStatementToInsertEntryIntoMultiReference(
		final String entityId,
		final String multiReferenceColumnId,
		final String referencedEntityId
	) {
		return
		"INSERT INTO "
		+ MultiContentTable.MULTI_REFERENCE_ENTRY.getFullName()
		+ " ("
		+ MultiReferenceEntryTableColumn.RECORD_ID.getName()
		+ ", "
		+ MultiReferenceEntryTableColumn.MULTI_REFERENCE_COLUMN_ID.getName()
		+ ", "
		+ MultiReferenceEntryTableColumn.REFERENCED_RECORD_ID.getName()
		+ ") VALUES ('"
		+ entityId
		+ "', '"
		+ multiReferenceColumnId
		+ "', '"
		+ referencedEntityId
		+ "')";
	}
}
