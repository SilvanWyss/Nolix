//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Identified;
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Named;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface ITable<

	E extends IEntity
>
extends IDatabaseObject, Identified, Named {
	
	//method declaration
	boolean containsEntityWithId(String id);
	
	//method declaration
	int getEntityCount();
	
	//method declaration
	Class<E> getEntityType();
	
	//method declaration
	IContainer<IColumn> getRefColumns();
	
	//method declaration
	IContainer<E> getRefEntities();
	
	//method declaration
	E getRefEntityById(String id);
	
	//method declaration
	IDatabase getRefParentDatabase();
	
	//method declaration
	ITable<E> insertEntity(E entity);
	
	//method declaration
	IContainer<E> technicalGetRefEntitiesInLocalData();
}
