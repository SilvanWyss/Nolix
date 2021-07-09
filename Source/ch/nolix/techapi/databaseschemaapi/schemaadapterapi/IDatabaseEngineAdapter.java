//package declaration
package ch.nolix.techapi.databaseschemaapi.schemaadapterapi;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.skillapi.IChangeSaver;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IBaseParametrizedPropertyType;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IColumn;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IDatabase;
import ch.nolix.techapi.databaseschemaapi.schemaapi.IDatabaseEngine;
import ch.nolix.techapi.databaseschemaapi.schemaapi.ITable;

//interface
public interface IDatabaseEngineAdapter<
	DE extends IDatabaseEngine<DE, D, T, C, PPT>,
	D extends IDatabase<D, T, C, PPT>,
	T extends ITable<T, C, PPT>,
	C extends IColumn<C, PPT>,
	PPT extends IBaseParametrizedPropertyType<Object>
>
extends IChangeSaver {
	
	//method
	default IDatabaseEngineAdapter<DE, D, T, C, PPT> addDatabase(final D database) {
		
		getRefDatabaseEngine().addDatabase(database);
		
		return this;
	}
	
	//method
	default boolean containsDatabaseWithName(final String name) {
		return getRefDatabaseEngine().containsDatabaseWithName(name);
	}
	
	//method
	default IDatabaseEngineAdapter<DE, D, T, C, PPT> createDatabaseWithName(final String name) {
		
		getRefDatabaseEngine().createDatabaseWithName(name);
		
		return this;
	}
	
	//method
	default void deleteDatabase(final D database) {
		getRefDatabaseEngine().deleteDatabase(database);
	}
	
	//method
	default void deleteDatabaseByName(final String name) {
		getRefDatabaseEngine().deleteDatabaseByName(name);
	}
	
	//method
	default int getDatabaseCount() {
		return getRefDatabaseEngine().getDatabaseCount();
	}
	
	//method
	default D getRefDatabaseByName(final String name) {
		return getRefDatabaseEngine().getRefDatabaseByName(name);
	}
	
	//method declaration
	DE getRefDatabaseEngine();
	
	//method
	default IContainer<D> getRefDatabases() {
		return getRefDatabaseEngine().getRefDatabases();
	}
}
