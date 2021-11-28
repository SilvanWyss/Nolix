//package declaration
package ch.nolix.techapi.objectschemaapi.schemaadapterapi;

//own imports
import ch.nolix.common.programcontrol.groupcloseable.GroupCloseable;
import ch.nolix.common.skillapi.IChangeSaver;
import ch.nolix.techapi.objectschemaapi.schemaapi.IDatabase;
import ch.nolix.techapi.objectschemaapi.schemaapi.ITable;

//interface
public interface ISchemaAdapter extends IChangeSaver, GroupCloseable {
	
	//method declaration
	ISchemaAdapter addTable(ITable table);
	
	//method declaration
	IDatabase getRefDatabase();
}
