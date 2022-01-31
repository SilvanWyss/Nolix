//package declaration
package ch.nolix.system.sqlrawobjectdata.sqlapi;

//interface
public interface IMultiValueStatementCreator {
	
	//method declaration
	String createStatementToDeleteEntriesFromMultiValue(String recordId, String multiValueColumnName);
	
	//method declaration
	String createStatementToDeleteEntryFromMultiValue(String recordId, String multiValueColumnId, String entry);
	
	//method declaration
	String createQueryToInsertEntryIntoMultiValue(String recordId, String multiValueColumnId, String entry);
}
