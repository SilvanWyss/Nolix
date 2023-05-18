//package declaration
package ch.nolix.system.sqlrawdata.sqlsyntaxapi;

//interface
public interface IMultiReferenceQueryCreator {
	
	//method declaration
	String createQueryToLoadMultiReferenceEntries(String entityId, String multiReferenceColumnId);
	
	//method declaration
	String createQueryToLoadOneOrNoneMultiReferenceEntryForGivenColumnAndReferencedEntity(
		String columnId,
		String referencedEntityId
	);
}
