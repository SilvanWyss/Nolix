//package declaration
package ch.nolix.techapi.intermediateschemaapi.schemaadapterapi;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.element.time.base.Time;
import ch.nolix.techapi.intermediateschemaapi.flatschemadtoapi.IFlatTableDTO;
import ch.nolix.techapi.intermediateschemaapi.schemadtoapi.IColumnDTO;

//interface
public interface IIntermediateSchemaReader {
	
	//method declaration
	boolean columnIsEmpty(String tableName, String columnHeader);
	
	//method declaration
	LinkedList<IColumnDTO> loadColumns(String tableName);
	
	//method declaration
	LinkedList<IFlatTableDTO> loadFlatTables();
	
	//method declaration
	Time loadSchemaTimestamp();
}
