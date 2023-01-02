//package declaration
package ch.nolix.system.sqlrawdata.mssql;

//own imports
import ch.nolix.system.sqlrawdata.sqlapi.IMultiReferenceQueryCreator;
import ch.nolix.system.sqlrawschema.multireferenceentrytable.MultiReferenceEntryTableColumn;
import ch.nolix.system.sqlrawschema.structure.MultiContentTable;

//class
public final class MultiReferenceQueryCreator implements IMultiReferenceQueryCreator {
	
	//method
	@Override
	public String createQueryToLoadMultiReferenceEntries(
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
		+ "';";
	}
	
	//method
	@Override
	public String createQueryToLoadOneOrNoneMultiReferenceEntryForGivenColumnAndReferencedEntity(
		final String columnId,
		final String referencedEntityId
	) {
		return
		"SELECT TOP 1 * FROM "
		+ MultiContentTable.MULTI_REFERENCE_ENTRY.getFullName()
		+ " WHERE "
		+ MultiReferenceEntryTableColumn.MULTI_REFERENCE_COLUMN_ID.getName()
		+ " = '"
		+ columnId
		+ "' AND "
		+ MultiReferenceEntryTableColumn.REFERENCED_ENTITY_ID.getName()
		+ " = '"
		+ referencedEntityId
		+ "';";
	}
}
