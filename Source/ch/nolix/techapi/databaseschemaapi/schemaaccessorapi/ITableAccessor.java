//package declaration
package ch.nolix.techapi.databaseschemaapi.schemaaccessorapi;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.techapi.databaseschemaapi.schemadtoapi.IColumnDTO;

//interface
public interface ITableAccessor {
	
	//method declaration
	void addColumnToCurrentTableToDatabase(IColumnDTO column);
	
	//method declaration
	void deleteColumnWithHeaderOfCurrentTableFromDatabaseWithHeader(String header);
	
	//method declaration
	String getNameOfCurrentTable();
	
	//method declaration
	IColumnAccessor getAccessorForColumnWithHeader(String header);
	
	//method declaration
	IContainer<IColumnDTO> loadColumnsOfCurrentTableFromDatabase();
	
	//method declaration
	void setNameForCurrentTableToDatabase(String name);
}
