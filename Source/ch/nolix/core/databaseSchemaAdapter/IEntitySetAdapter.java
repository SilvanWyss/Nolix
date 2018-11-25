//package declaration
package ch.nolix.core.databaseSchemaAdapter;

import ch.nolix.core.skillAPI.Named;

//interface
public interface IEntitySetAdapter extends Named {
	
	//abstract method
	public abstract IColumnAdapter getColumnAdapter(Column column);
}
