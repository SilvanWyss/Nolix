//package declaration
package ch.nolix.techapi.rawobjectschemaapi.schemaadapterapi;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.element.time.base.Time;
import ch.nolix.techapi.rawobjectschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.techapi.rawobjectschemaapi.schemadtoapi.IColumnDTO;

//interface
public interface ISchemaReader {
	
	//method declaration
	boolean columnIsEmpty(String tableName, String columnHeader);
	
	//method declaration
	LinkedList<IColumnDTO> loadColumns(String tableName);
	
	//method declaration
	LinkedList<IFlatTableDTO> loadFlatTables();
	
	//method declaration
	Time loadSchemaTimestamp();
}
