//package declaration
package ch.nolix.system.databaseSchemaAdapter;

//own imports
import ch.nolix.common.attributeAPI.Named;
import ch.nolix.common.container.LinkedList;

//interface
public interface IEntitySetAdapter extends Named {
	
	//method declaration
	public abstract IColumnAdapter getColumnAdapter(Column column);
	
	//method declaration
	public abstract LinkedList<IColumnAdapter> getColumnAdapters();
	
	//method
	public default LinkedList<Column> getColumns() {
		return getColumnAdapters().to(IColumnAdapter::toColumn);
	}
	
	//method declaration
	public default EntitySet toEntitySet() {
		return new EntitySet(getName(), getColumns());
	}
}
