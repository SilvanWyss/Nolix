//package declaration
package ch.nolix.system.sqlrawdata.sqlapi;

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
