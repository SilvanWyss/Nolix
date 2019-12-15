//package declaration
package ch.nolix.system.databaseSchemaAdapter;

//own imports
import ch.nolix.common.attributeAPI.Named;
import ch.nolix.system.databaseAdapter.EntityType;

//interface
public interface IEntitySetAdapter extends Named {
	
	//method declaration
	public abstract IColumnAdapter getColumnAdapter(Column column);
	
	//default method
	public default EntityType<?> createEntityType() {
		//TODO: Implement.
		return null;
	}
}
