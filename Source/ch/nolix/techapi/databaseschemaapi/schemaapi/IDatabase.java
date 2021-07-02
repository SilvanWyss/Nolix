//package declaration
package ch.nolix.techapi.databaseschemaapi.schemaapi;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.generalskillapi.IFluentObject;

//interface
public interface IDatabase<
	D extends IDatabase<D, T, C, PPT>,
	T extends ITable<T, C, PPT>,
	C extends IColumn<C, PPT>,
	PPT extends IParametrizedPropertyType<Object>
>
extends IFluentObject<D>, Named {
	
	//method declaration
	D addTable(T table);
	
	//method
	default D addTableWithName(final String name) {
		
		final var table = createTableObject().setName(name);
		addTable(table);
		
		return asConcrete();
	}
	
	//method
	default boolean containsTableWithName(final String name) {
		return getRefTables().contains(t -> t.hasName(name));
	}
	
	//method declaration
	T createTableObject();
	
	//method
	void deleteTable(T table);
	
	//method
	default void deleteTableWithName(final String name) {
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
