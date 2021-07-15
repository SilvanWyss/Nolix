//package declaration
package ch.nolix.techapi.databaseschemaapi.extendedschemaapi;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.techapi.databasecommonapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.techapi.databaseschemaapi.schemaaccessorapi.IDatabaseAccessor;
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
extends IDatabase<ED, ET, EC, EPPT>, IDatabaseObject {
	
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
	
	//method declaration
	void setAccessorForActualDatabase(IDatabaseAccessor databaseAccessor);
}
