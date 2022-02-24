//package declaration
package ch.nolix.system.sqlrawdata.sqlapi;

//interface
public interface IMultiReferenceStatementCreator {
	
	//method declaration
	String createStatementToDeleteEntriesFromMultiReference(String entityId, String multiReferenceColumnId);
	
	//method declaration
	String createStatementToDeleteEntryFromMultiReference(
		String entityId,
		String multiReferenceColumnId,
		String referencedEntityId
	);
	
	//method declaration
	String createStatementToInsertEntryIntoMultiReference(
		String entityId,
		String multiReferenceColumnId,
		String referencedEntityId
	);
}
