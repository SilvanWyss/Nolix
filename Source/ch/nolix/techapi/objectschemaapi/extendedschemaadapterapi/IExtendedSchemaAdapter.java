//package declaration
package ch.nolix.techapi.objectschemaapi.extendedschemaadapterapi;

import ch.nolix.techapi.objectschemaapi.extendedschemaapi.IExtendedColumn;
import ch.nolix.techapi.objectschemaapi.extendedschemaapi.IExtendedDatabase;
import ch.nolix.techapi.objectschemaapi.extendedschemaapi.IExtendedParametrizedPropertyType;
import ch.nolix.techapi.objectschemaapi.extendedschemaapi.IExtendedTable;
import ch.nolix.techapi.objectschemaapi.schemaadapterapi.ISchemaAdapter;

//interface
public interface IExtendedSchemaAdapter<
	ED extends IExtendedDatabase<ED, ET, EC, EPPT>,
	ET extends IExtendedTable<ET, EC, EPPT>,
	EC extends IExtendedColumn<EC, EPPT>,
	EPPT extends IExtendedParametrizedPropertyType<?>
> extends ISchemaAdapter<ED, ET, EC, EPPT> {
	
	//method
	default IExtendedSchemaAdapter<ED, ET, EC, EPPT> addTable(ET table) {
		
		getRefDatabase().addTable(table);
		
		return this;
	}
	
	//method
	@SuppressWarnings("unchecked")
	default IExtendedSchemaAdapter<ED, ET, EC, EPPT> addTable(ET... tables) {
		
		for (final var t : tables) {
			getRefDatabase().addTable(t);
		}
		
		return this;
	}
	
	//method
	default boolean containsTableWithName(final String name) {
		return getRefDatabase().containsTableWithName(name);
	}
	
	//method
	default IExtendedSchemaAdapter<ED, ET, EC, EPPT> createTableWithName(final String name) {
		
		getRefDatabase().createTableWithName(name);
		
		return this;
	}
	
	//method
	default void deleteTableByName(final String name) {
		getRefDatabase().deleteTableByName(name);
	}
	
	//method
	default ET getRefTableByName(final String name) {
		return getRefDatabase().getRefTableByName(name);
	}
	
	//method
	default int getTableCount() {
		return getRefDatabase().getTableCount();
	}
}
