//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Identified;
import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.Named;
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
	IContainer<IColumn> getOriColumns();
	
	//method declaration
	IContainer<E> getOriEntities();
	
	//method declaration
	E getOriEntityById(String id);
	
	//method declaration
	E getOriEntityByIdOrNull(String id);
	
	//method declaration
	IDatabase getOriParentDatabase();
	
	//method declaration
	ITable<E> insertEntity(E entity);
	
	//method declaration
	IContainer<E> technicalGetRefEntitiesInLocalData();
}
