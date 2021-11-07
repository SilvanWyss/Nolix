//package declaration
package ch.nolix.techapi.objectschemaapi.extendedschemaadapterapi;

import ch.nolix.common.container.IContainer;
import ch.nolix.techapi.objectschemaapi.extendedschemaapi.IExtendedColumn;
import ch.nolix.techapi.objectschemaapi.extendedschemaapi.IExtendedDatabase;
import ch.nolix.techapi.objectschemaapi.extendedschemaapi.IExtendedDatabaseEngine;
import ch.nolix.techapi.objectschemaapi.extendedschemaapi.IExtendedParametrizedPropertyType;
import ch.nolix.techapi.objectschemaapi.extendedschemaapi.IExtendedTable;
import ch.nolix.techapi.objectschemaapi.schemaadapterapi.IDatabaseEngineAdapter;

//interface
public interface IExtendedDatabaseEngineAdapter<
	EDE extends IExtendedDatabaseEngine<EDE, ED, ET, EC, EPPT>,
	ED extends IExtendedDatabase<ED, ET, EC, EPPT>,
	ET extends IExtendedTable<ET, EC, EPPT>,
	EC extends IExtendedColumn<EC, EPPT>,
	EPPT extends IExtendedParametrizedPropertyType<EPPT, ?>
> extends IDatabaseEngineAdapter<EDE, ED, ET, EC, EPPT> {
	
	//method
	default IExtendedDatabaseEngineAdapter<EDE, ED, ET, EC, EPPT> addDatabase(final ED database) {
		
		getRefDatabaseEngine().addDatabase(database);
		
		return this;
	}
	
	//method
	default boolean containsDatabaseWithName(final String name) {
		return getRefDatabaseEngine().containsDatabaseWithName(name);
	}
	
	//method
	default IExtendedDatabaseEngineAdapter<EDE, ED, ET, EC, EPPT> createDatabaseWithName(final String name) {
		
		getRefDatabaseEngine().createDatabaseWithName(name);
		
		return this;
	}
	
	//method
	default int getDatabaseCount() {
		return getRefDatabaseEngine().getDatabaseCount();
	}
	
	//method
	default ED getRefDatabaseByName(final String name) {
		return getRefDatabaseEngine().getRefDatabaseByName(name);
	}
	
	//method
	default IContainer<ED> getRefDatabases() {
		return getRefDatabaseEngine().getRefDatabases();
	}
}
