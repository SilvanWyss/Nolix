//package declaration
package ch.nolix.techapi.sqloptimisticlockingdataapi.sqldatalanguageapi;

//interface
public interface IDataQueryCreator {
	
	//method declaration
	String createQueryToLoadAllRecordsFromTable(String tableName);
}
