//package declaration
package ch.nolix.techapi.databaseschemaapi.schemaapi;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.common.container.IContainer;
import ch.nolix.techapi.databasecommonapi.databaseobjectapi.IDatabaseObject;
import ch.nolix.techapi.databaseschemaapi.intermediateschemaapi.IIntermediateSchemaAdapter;

//interface
public interface IDatabase<
	D extends IDatabase<D, T, C, PPT>,
	T extends ITable<T, C, PPT>,
	C extends IColumn<C, PPT>,
	PPT extends IParametrizedPropertyType<?>
> extends IDatabaseObject, Named {
	
	//method declaration
	D addTable(T table);
	
	//method declaration
	boolean belongsToEngine();
	
	//method declaration
	D createTableWithName(String name);
	
	//method declaration
	IDatabaseEngine<?, D, T, C, PPT> getParentEngine();
	
	//method declaration
	IContainer<T> getRefTables();
	
	//method declaration
	void setRealSchemaAdapter(IIntermediateSchemaAdapter intermediateSchemaAdapter);
}
