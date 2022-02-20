//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaadapterapi;

//own imports
import ch.nolix.core.skillapi.IMultiTimeChangeSaver;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//interface
public interface ISchemaAdapter<IMPL> extends IMultiTimeChangeSaver {
	
	//method declaration
	ISchemaAdapter<IMPL> addTable(ITable<IMPL> table);
}
