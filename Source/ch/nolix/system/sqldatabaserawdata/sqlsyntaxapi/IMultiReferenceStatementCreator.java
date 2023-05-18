//package declaration
package ch.nolix.system.sqldatabaserawdata.sqlsyntaxapi;

//interface
public interface IMultiReferenceStatementCreator {
	
	//method declaration
	String createStatementToDeleteMultiReferenceEntries(String entityId, String multiReferenceColumnId);
	
	//method declaration
	String createStatementToDeleteMultiReferenceEntry(
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
