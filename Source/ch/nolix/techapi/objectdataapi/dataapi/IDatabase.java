//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.techapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface IDatabase<IMPL> extends IDatabaseObject {
	
	//method declaration
	ITable<IMPL> getRefTableByName(String name);
}
