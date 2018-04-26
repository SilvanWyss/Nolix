//package declaration
package ch.nolix.core.databaseSchemaAdapter;

//own import
import ch.nolix.core.interfaces.Named;

//interface
public interface IEntitySetConnector<C> extends Named {

	//abstract method
	public abstract C createCommandForAdd(Column column);
	
	//abstract method
	public abstract C createCommandForDelete(Column column);
	
	//abstract method
	public abstract C createCommandForRename(String name);
	
	//abstract method
	public abstract IColumnConnector<C> getColumnConnector(Column column);
}
