//package declaration
package ch.nolix.system.sqlrawdata.sqlapi;

//interface
public interface IMultiReferenceQueryCreator {
	
	//method declaration
	String createQueryToLoadAllMultiReferenceEntriesForRecord(String entityId, String multiReferenceColumnId);
}
