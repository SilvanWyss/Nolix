//package declaration
package ch.nolix.techapi.databaseschemaapi.schemaapi;

//own imports
import ch.nolix.common.container.IContainer;

//interface
public interface IDatabaseEngine<
	DE extends IDatabaseEngine<DE, D, T, C, PPT>,
	D extends IDatabase<D, T, C, PPT>,
	T extends ITable<T, C, PPT>,
	C extends IColumn<C, PPT>,
	PPT extends IParametrizedPropertyType<Object>
> {
	
	//method declaration
	DE addDatabase(D database);
	
	//method
	default boolean containsDatabaseWithName(final String name) {
		return getRefDatabases().contains(db -> db.containsTableWithName(name));
	}
	
	//method declaration
	DE createDatabaseWithName(String name);
	
	//method declaration
	void deleteDatabase(D database);
	
	//method
	default void deleteDatabaseByName(final String name) {
		deleteDatabase(getRefDatabaseByName(name));
	}
	
	//method
	default int getDatabaseCount() {
		return getRefDatabases().getElementCount();
	}
	
	//method
	default D getRefDatabaseByName(final String name) {
		return getRefDatabases().getRefFirst(db -> db.hasName(name));
	}
	
	//method declaration
	IContainer<D> getRefDatabases();
}
