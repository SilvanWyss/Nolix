//package declaration
package ch.nolix.techapi.objectdataapi.dataapi;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.techapi.databaseapi.databaseobjectapi.IDatabaseObject;

//interface
public interface ITable<
	T extends ITable<T, E, P>,
	E extends IEntity<E, P>,
	P extends IProperty<P>
> extends IDatabaseObject {
	
	//method declaration
	T addEntity(E entity);
	
	//method declaration
	IDatabase<?, ?, ?, ?> getParentDatabase();
	
	//method declaration
	IContainer<E> getRefAllEntities();
	
	//method declaration
	E getRefEntityById(String id);
	
	//method
	IContainer<IColumn<P>> getReferencingColumns();
}
