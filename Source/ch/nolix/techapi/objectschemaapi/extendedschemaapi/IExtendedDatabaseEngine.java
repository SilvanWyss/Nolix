//package declaration
package ch.nolix.techapi.objectschemaapi.extendedschemaapi;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.techapi.objectschemaapi.schemaapi.IDatabaseEngine;

//interface
public interface IExtendedDatabaseEngine<
	EDE extends IExtendedDatabaseEngine<EDE, ED, ET, EC, EPPT>,
	ED extends IExtendedDatabase<ED, ET, EC, EPPT>,
	ET extends IExtendedTable<ET, EC, EPPT>,
	EC extends IExtendedColumn<EC, EPPT>,
	EPPT extends IExtendedParametrizedPropertyType<EPPT, ?>
> extends IDatabaseEngine<EDE, ED, ET, EC, EPPT> {
	
	//method
	default void assertDoesNotContainDatabaseWithName(final String name) {
		if (containsDatabaseWithName(name)) {
			throw new InvalidArgumentException(this, "contains already a database with the name '" + name + "'");
		}
	}
	
	//method
	default boolean containsDatabaseWithName(final String name) {
		return getRefDatabases().containsAny(db -> db.containsTableWithName(name));
	}
	
	//method
	default int getDatabaseCount() {
		return getRefDatabases().getElementCount();
	}
	
	//method
	default ED getRefDatabaseByName(final String name) {
		return getRefDatabases().getRefFirst(db -> db.hasName(name));
	}
}
