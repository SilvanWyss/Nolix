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
	PPT extends IParametrizedPropertyType<Object>
>
extends IChangeSaver {
	
	//method
	default IDatabaseSchemaAdapter<D, T, C, PPT> addTable(T table) {
		
		getRefDatabase().addTable(table);
		
		return this;
	}
	
	//method
	default IDatabaseSchemaAdapter<D, T, C, PPT> addTableWithName(final String name) {
		
		getRefDatabase().addTableWithName(name);
		
		return this;
	}
	
	//method
	default boolean containsTableWithName(final String name) {
		return getRefDatabase().containsTableWithName(name);
	}
	
	//method
	default void deleteTable(T table) {
		getRefDatabase().deleteTable(table);
	}
	
	//method
	default void deleteTableWithName(final String name) {
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
