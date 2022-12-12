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
> extends IDatabaseObject, IdentifiedByString, Named {
	
	//method declaration
	IContainer<IColumn<IMPL>> getColumns();
	
	//method declaration
	Class<E> getEntityClass();
	
	//method declaration
	IDatabase<IMPL> getRefParentDatabase();
	
	//method declaration
	IContainer<E> getRefAllEntities();
	
	//method declaration
	E getRefEntityById(String id);
	
	//method declaration
	ITable<IMPL, E> insert(E entity);
	
	//method declaration
	IContainer<E> technicalGetRefEntitiesInLocalData();
}
