//package declaration
package ch.nolix.system.databaseAdapter;

import ch.nolix.core.attributeAPI.Named;
//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;

//interface
public interface IEntitySetAdapter<E extends Entity> extends Named {
	
	//abstract method
	public abstract boolean containsEntity(long id);
	
	//abstract method
	public abstract List<E> getEntities(EntityType<E> entityType);
	
	//abstract method
	public List<E> getEntities(IContainer<Long> ids, EntityType<E> entityType);
	
	//abstract method
	public abstract E getEntity(long id, EntityType<E> entityType);
}
