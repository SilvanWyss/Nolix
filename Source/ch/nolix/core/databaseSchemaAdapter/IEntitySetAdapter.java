//package declaration
package ch.nolix.core.databaseSchemaAdapter;

//own imports
import ch.nolix.core.databaseAdapter.EntityType;
import ch.nolix.core.skillAPI.Named;

//interface
public interface IEntitySetAdapter extends Named {
	
	//abstract method
	public abstract IColumnAdapter getColumnAdapter(Column column);
	
	//default method
	public default EntityType<?> createArtificialEntityType() {
		//TODO
		return null;
	}
}
