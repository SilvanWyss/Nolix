//package declaration
package ch.nolix.core.databaseAdapter;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.skillInterfaces.Named;

//interface
public interface IEntitySetConnector<E extends Entity>
extends Named {
	
	//abstract method
	public abstract boolean containsEntity(long id);
	
	//abstract method
	public abstract List<E> getEntities(EntityType<E> entityType);
	
	//abstract method
	public abstract E getEntity(long id, EntityType<E> entityType);
}
