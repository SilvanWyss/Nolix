//package declaration
package ch.nolix.system.entity;

//own imports
import ch.nolix.common.attributeAPI.Named;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.ReadContainer;
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
		return addEntities(ReadContainer.forArray(entities));
	}
	
	//method declaration
	public abstract <E2 extends Entity> boolean canReferenceBackEntityOfType(final Class<E2> type);
	
	//method declaration
	public abstract <E2 extends Entity> boolean canReferenceEntityOfType(final Class<E2> type);
	
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
