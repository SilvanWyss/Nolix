//package declaration
package ch.nolix.system.sqlrawobjectdata.sqlapi;

//interface
public interface IMultiValueStatementCreator {
	
	//method declaration
	String createStatementToDeleteEntriesFromMultiValue(String entityId, String multiValueColumnName);
	
	//method declaration
	String createStatementToDeleteEntryFromMultiValue(String entityId, String multiValueColumnId, String entry);
	
	//method declaration
	String createQueryToInsertEntryIntoMultiValue(String entityId, String multiValueColumnId, String entry);
}
