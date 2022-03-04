//package declaration
package ch.nolix.systemapi.objectdataapi.schemamapperapi;

//own imports
import ch.nolix.core.container.LinkedList;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;

//interface
public interface IColumnMapper<IMPL> {
	
	//method declaration
	IColumn<IMPL> createColumnFrom(IProperty<?> property);
	
	//method declaration
	LinkedList<IColumn<IMPL>> createColumnsFrom(Class<IEntity<?>> entityType);
	
	//method declaration
	LinkedList<IColumn<IMPL>> createColumnsFrom(IEntity<?> entity);
}
