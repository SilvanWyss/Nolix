//package declaration
package ch.nolix.techapi.databaseschemaapi.extendedschemaapi;

//own imports
import ch.nolix.techapi.databaseschemaapi.schemaapi.IDatabaseEngine;

//interface
public interface IExtendedDatabaseEngine<
	EDE extends IExtendedDatabaseEngine<EDE, ED, ET, EC, EPPT>,
	ED extends IExtendedDatabase<ED, ET, EC, EPPT>,
	ET extends IExtendedTable<ET, EC, EPPT>,
	EC extends IExtendedColumn<EC, EPPT>,
	EPPT extends IExtendedParametrizedPropertyType<?>
> extends IDatabaseEngine<EDE, ED, ET, EC, EPPT> {
	
	//method
	default boolean containsDatabaseWithName(final String name) {
		return getRefDatabases().containsAny(db -> db.containsTableWithName(name));
	}
	
	//method
	default void deleteDatabaseByName(final String name) {
		deleteDatabase(getRefDatabaseByName(name));
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
