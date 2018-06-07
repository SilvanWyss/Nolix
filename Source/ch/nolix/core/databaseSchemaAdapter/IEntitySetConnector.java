//package declaration
package ch.nolix.core.databaseSchemaAdapter;

//own import
import ch.nolix.core.interfaces.Named;

//interface
public interface IEntitySetConnector extends Named {
	
	//abstract method
	public abstract IColumnConnector getColumnConnector(Column column);
}
