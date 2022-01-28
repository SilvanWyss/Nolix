//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.common.container.IContainer;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface ITable<
	IMPL,
	E extends IEntity<IMPL>
> extends IDatabaseObject, Named {
	
	//method declaration
	boolean containsEntityWithGivenIdInLocalData(String id);
	
	//method declaration
	Class<E> getEntityClass();
	
	//method declaration
	IDatabase<IMPL> getParentDatabase();
	
	//method declaration
	IContainer<E> getRefAllEntities();
	
	//method declaration
	E getRefEntityById(String id);
	
	//method declaration
	ITable<IMPL, E> insert(E entity);
	
	//method declaration
	IContainer<E> technicalGetRefEntitiesInLocalData();
}
