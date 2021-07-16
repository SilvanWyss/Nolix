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
	PPT extends IParametrizedPropertyType<?>
> {
	
	//method declaration
	DE addDatabase(D database);
	
	//method declaration
	DE createDatabaseWithName(String name);
	
	//method declaration
	void deleteDatabase(D database);
	
	//method declaration
	IContainer<D> getRefDatabases();
}
