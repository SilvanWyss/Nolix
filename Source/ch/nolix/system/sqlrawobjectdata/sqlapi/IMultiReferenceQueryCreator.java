//package declaration
package ch.nolix.system.sqlrawobjectdata.sqlapi;

//interface
public interface IMultiReferenceQueryCreator {
	
	//method declaration
	String createQueryToLoadAllMultiReferenceEntriesForRecord(String recordId, String multiReferenceColumnId);
}
