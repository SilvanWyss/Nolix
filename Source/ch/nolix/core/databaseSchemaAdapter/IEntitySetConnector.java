//package declaration
package ch.nolix.core.databaseSchemaAdapter;

import ch.nolix.core.skillAPI.Named;

//interface
public interface IEntitySetConnector extends Named {
	
	//abstract method
	public abstract IColumnConnector getColumnConnector(Column column);
}
