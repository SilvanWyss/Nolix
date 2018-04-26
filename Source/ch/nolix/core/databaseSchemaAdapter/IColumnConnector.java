//package declaration
package ch.nolix.core.databaseSchemaAdapter;

//own import
import ch.nolix.core.interfaces.Headered;

//interface
public interface IColumnConnector<C> extends Headered {

	//abstract method
	public abstract C createCommandForRename(String header);
}
