//package declaration
package ch.nolix.core.databaseAdapter;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.interfaces.Named;

//interface
public interface IEntitySetConnector<E extends Entity, C>
extends Named {

	//abstract method
	public abstract C createCommandForAdd(E entity);
	
	//abstract method
	public abstract C createCommandForDelete(E entity);
	
	//abstract method
	public abstract C createCommandForUpdate(E entity);
	
	//abstract method
	public abstract List<E> getEntities(EntityType<E> entityType);
	
	//abstract method
	public abstract E getEntity(int id, EntityType<E> entityType);
}
