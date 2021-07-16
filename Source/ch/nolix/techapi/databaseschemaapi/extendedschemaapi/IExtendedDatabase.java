//package declaration
package ch.nolix.techapi.databaseschemaapi.extendedschemaapi;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.techapi.databasecommonapi.databaseobjectapi.IExtendedDatabaseObject;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IDatabase;
import ch.nolix.techapi.databaseschemaapi.schemaapi.ITable;

//interface
public interface IExtendedDatabase<
	ED extends IExtendedDatabase<ED, ET, EC, EPPT>,
	ET extends IExtendedTable<ET, EC, EPPT>,
	EC extends IExtendedColumn<EC, EPPT>,
	EPPT extends IExtendedParametrizedPropertyType<?>
>
extends IDatabase<ED, ET, EC, EPPT>, IExtendedDatabaseObject {
	
	//method
	default void assertContainsTable(final ITable<?, ?, ?> table) {
		if (!containsTable(table)) {
			throw new ArgumentDoesNotContainElementException(this, table);
		}
	}
	
	//method
	default void assertContainsTableWithColumn(final IColumn<?, ?> column) {
		if (!containsTableWithColumn(column)) {
			throw new ArgumentDoesNotContainElementException(this, column);
		}
	}
	
	//method
	default void assertDoesNotContainTableWithName(final String name) {
		if (containsTableWithName(name)) {
			throw new InvalidArgumentException(this, "contains already a table with the name '" + name + "'");
		}
	}
	
	//method
	default boolean containsTable(final ITable<?, ?, ?> table) {
		return getRefTables().contains(table);
	}
	
	//method
	default boolean containsTableWithColumn(final IColumn<?, ?> column) {
		return getRefTables().containsAny(t -> t.containsColumn(column));
	}
	
	//method
	default boolean containsTableWithName(final String name) {
		return getRefTables().containsAny(t -> t.hasName(name));
	}
	
	//method
	default void deleteTableByName(final String name) {
		getRefTableByName(name).delete();
	}
	
	//method
	default ET getRefTableByName(final String name) {
		return getRefTables().getRefFirst(t -> t.hasName(name));
	}
	
	//method
	default int getTableCount() {
		return getRefTables().getElementCount();
	}

}
