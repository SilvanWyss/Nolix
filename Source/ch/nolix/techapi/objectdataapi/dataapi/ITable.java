//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.common.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.common.container.IContainer;
import ch.nolix.techapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface ITable<
	IMPL,
	E extends IEntity<IMPL>
> extends IDatabaseObject, Named {
	
	//method declaration
	Class<E> getEntityClass();
	
	//method declaration
	IDatabase<IMPL> getParentDatabase();
	
	//method declaration
	IContainer<E> getRefAllEntities();
	
	//method declaration
	E getRefEntityById(String id);
	
	//method
	IContainer<IColumn<IMPL>> getReferencingColumns();
	
	//method declaration
	boolean hasInsertedEntityWithGivenIdInLocalData(String id);
	
	//method declaration
	ITable<IMPL, E> insert(E entity);
	
	//method declaration
	IContainer<E> technicalGetRefEntitiesInLocalData();
}
