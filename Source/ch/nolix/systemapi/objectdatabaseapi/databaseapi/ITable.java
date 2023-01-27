//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//own imports
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.IdentifiedByString;
import ch.nolix.coreapi.attributeapi.mandatoryattributeuniversalapi.Named;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.systemapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface ITable<
	IMPL,
	E extends IEntity<IMPL>
>
extends IDatabaseObject, IdentifiedByString, Named {
	
	//method declaration
	boolean containsEntityWithId(String id);
	
	//method declaration
	Class<E> getEntityType();
	
	//method declaration
	IContainer<IColumn<IMPL>> getRefColumns();
	
	//method declaration
	IContainer<E> getRefEntities();
	
	//method declaration
	E getRefEntityById(String id);
	
	//method declaration
	IDatabase<IMPL> getRefParentDatabase();
	
	//method declaration
	ITable<IMPL, E> insertEntity(E entity);
	
	//method declaration
	IContainer<E> technicalGetRefEntitiesInLocalData();
}
