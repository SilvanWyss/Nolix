//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.common.container.IContainer;
import ch.nolix.techapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface ITable<IMPL> extends IDatabaseObject, Named {
	
	//method declaration
	IDatabase<IMPL> getParentDatabase();
	
	//method declaration
	IContainer<IEntity<IMPL>> getRefAllEntities();
	
	//method declaration
	IEntity<IMPL> getRefEntityById(String id);
	
	//method
	IContainer<IColumn<IMPL>> getReferencingColumns();
	
	//method declaration
	boolean hasInsertedEntityWithId(String id);
	
	//method declaration
	ITable<IMPL> insert(IEntity<IMPL> entity);
}
