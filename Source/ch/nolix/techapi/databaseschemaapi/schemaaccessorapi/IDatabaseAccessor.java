//package declaration
package ch.nolix.techapi.databaseschemaapi.schemaaccessorapi;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.techapi.databaseschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.techapi.databaseschemaapi.schemadtoapi.ITableDTO;

//interface
public interface IDatabaseAccessor {
	
	//method declaration
	void addTableToCurrentDatabase(ITableDTO table);
	
	//method declaration
	void deleteTableWithNameFromDatabase(String name);
	
	//method declaration
	String getNameOfCurrentDatabase();
	
	//method declaration
	IContainer<IFlatTableDTO> loadFlatTablesFromCurrentDatabase();
}
