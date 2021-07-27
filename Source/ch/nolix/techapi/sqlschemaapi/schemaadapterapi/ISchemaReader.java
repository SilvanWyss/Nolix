//package declaration
package ch.nolix.techapi.sqlschemaapi.schemaadapterapi;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.techapi.sqlschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.techapi.sqlschemaapi.schemadtoapi.IColumnDTO;

//interface
public interface ISchemaReader {
	
	//method declaration
	LinkedList<IColumnDTO> loadColumns(String tableName);
	
	//method declaration
	LinkedList<IFlatTableDTO> loadFlatTables();
}
