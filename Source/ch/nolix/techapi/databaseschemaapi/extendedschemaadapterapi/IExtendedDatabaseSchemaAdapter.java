//package declaration
package ch.nolix.techapi.databaseschemaapi.extendedschemaadapterapi;

//own imports
import ch.nolix.techapi.databaseschemaapi.extendedschemaapi.IExtendedColumn;
import ch.nolix.techapi.databaseschemaapi.extendedschemaapi.IExtendedDatabase;
import ch.nolix.techapi.databaseschemaapi.extendedschemaapi.IExtendedParametrizedPropertyType;
import ch.nolix.techapi.databaseschemaapi.extendedschemaapi.IExtendedTable;
import ch.nolix.techapi.databaseschemaapi.schemaadapterapi.IDatabaseSchemaAdapter;

//interface
public interface IExtendedDatabaseSchemaAdapter<
	ED extends IExtendedDatabase<ED, ET, EC, EPPT>,
	ET extends IExtendedTable<ET, EC, EPPT>,
	EC extends IExtendedColumn<EC, EPPT>,
	EPPT extends IExtendedParametrizedPropertyType<?>
> extends IDatabaseSchemaAdapter<ED, ET, EC, EPPT> {
	
	//method
	default IExtendedDatabaseSchemaAdapter<ED, ET, EC, EPPT> addTable(ET table) {
		
		getRefDatabase().addTable(table);
		
		return this;
	}
	
	//method
	default boolean containsTableWithName(final String name) {
		return getRefDatabase().containsTableWithName(name);
	}
	
	//method
	default IExtendedDatabaseSchemaAdapter<ED, ET, EC, EPPT> createTableWithName(final String name) {
		
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
