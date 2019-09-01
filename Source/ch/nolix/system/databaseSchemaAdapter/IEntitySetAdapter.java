//package declaration
package ch.nolix.system.databaseSchemaAdapter;

import ch.nolix.common.attributeAPI.Named;
import ch.nolix.system.databaseAdapter.EntityType;

//interface
public interface IEntitySetAdapter extends Named {
	
	//abstract method
	public abstract IColumnAdapter getColumnAdapter(Column column);
	
	//default method
	public default EntityType<?> createEntityType() {
		//TODO: Create a general Entity class. 
		return null;
	}
}
