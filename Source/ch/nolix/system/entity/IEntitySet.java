//package declaration
package ch.nolix.system.entity;

//own imports
import ch.nolix.common.attributeAPI.Named;
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.ReadContainer;
import ch.nolix.common.skillAPI.Clearable;

//interface
public interface IEntitySet<E extends Entity> extends Clearable<IEntitySet<E>>, Named {
	
	//method
	public default IEntitySet<E> addEntities(final Iterable<E> entities) {
		
		entities.forEach(this::addEntity);
		
		return this;
	}
	
	//method declaration
	public abstract IEntitySet<E> addEntity(E entity);
	
	//method
	@SuppressWarnings("unchecked")
	public default IEntitySet<E> addEntity(final E... entities) {
		return addEntities(new ReadContainer<>(entities));
	}
	
	//method declaration
	public abstract IEntitySet<E> deleteEntity(E entity);
	
	//method declaration
	public abstract IDatabaseAdapter getParentDatabaseAdapter();
	
	//method declaration
	public abstract IContainer<E> getRefEntities();
	
	//method declaration
	public abstract E getRefEntityById(long id);
	
	//method declaration
	public abstract boolean hasChanges();
		
	//method declaration
	public abstract boolean references(Entity entity);
	
	//method declaration
	public abstract void noteMutatedEntity(E entity);
}
