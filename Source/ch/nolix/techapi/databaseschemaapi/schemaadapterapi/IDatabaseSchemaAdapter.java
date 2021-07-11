//package declaration
package ch.nolix.techapi.databaseschemaapi.schemaadapterapi;

//own imports
import ch.nolix.common.skillapi.IChangeSaver;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IDatabase;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IParametrizedPropertyType;
import ch.nolix.techapi.databaseschemaapi.schemaapi.ITable;

//interface
public interface IDatabaseSchemaAdapter<
	D extends IDatabase<D, T, C, PPT>,
	T extends ITable<T, C, PPT>,
	C extends IColumn<C, PPT>,
	PPT extends IParametrizedPropertyType<?>
> extends IChangeSaver {
	
	//method
	default IDatabaseSchemaAdapter<D, T, C, PPT> addTable(T table) {
		
		getRefDatabase().addTable(table);
		
		return this;
	}
	
	//method
	default boolean containsTableWithName(final String name) {
		return getRefDatabase().containsTableWithName(name);
	}
	
	//method
	default IDatabaseSchemaAdapter<D, T, C, PPT> createTableWithName(final String name) {
		
		getRefDatabase().createTableWithName(name);
		
		return this;
	}
	
	//method
	default void deleteTable(T table) {
		getRefDatabase().deleteTable(table);
	}
	
	//method
	default void deleteTableByName(final String name) {
		deleteTable(getRefTableByName(name));
	}
	
	//method declaration
	D getRefDatabase();
	
	//method
	default T getRefTableByName(final String name) {
		return getRefDatabase().getRefTableByName(name);
	}
	
	//method
	default int getTableCount() {
		return getRefDatabase().getTableCount();
	}
}
