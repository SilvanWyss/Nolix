//package declaration
package ch.nolix.techapi.databaseschemaapi.schemaapi;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.common.container.IContainer;

//interface
public interface IDatabase<
	D extends IDatabase<D, T, C, PPT>,
	T extends ITable<T, C, PPT>,
	C extends IColumn<C, PPT>,
	PPT extends IParametrizedPropertyType<Object>
>
extends Named {
	
	//method declaration
	D addTable(T table);
	
	//method
	default boolean containsTableWithName(final String name) {
		return getRefTables().containsAny(t -> t.hasName(name));
	}
	
	//method declaration
	D createTableWithName(String name);
	
	//method
	void deleteTable(T table);
	
	//method
	default void deleteTableByName(final String name) {
		deleteTable(getRefTableByName(name));
	}
	
	//method
	default T getRefTableByName(final String name) {
		return getRefTables().getRefFirst(t -> t.hasName(name));
	}
	
	//method
	default int getTableCount() {
		return getRefTables().getElementCount();
	}
	
	//method declaration
	IContainer<T> getRefTables();
}
