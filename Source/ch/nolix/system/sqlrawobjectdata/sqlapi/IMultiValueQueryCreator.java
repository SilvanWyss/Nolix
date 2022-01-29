//package declaration
package ch.nolix.system.sqlrawobjectdata.sqlapi;

//interface
public interface IMultiValueQueryCreator {
	
	//method declaration
	String createQueryToLoadMultiValueEntriesFromRecord(String recordId, String multiValueColumnId);
}
