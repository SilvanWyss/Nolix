//package declaration
package ch.nolix.system.entity;

//own imports
import ch.nolix.common.attributeAPI.Named;
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.ReadContainer;
import ch.nolix.common.functionAPI.IElementTakerBooleanGetter;
import ch.nolix.common.skillAPI.Clearable;

//interface
public interface IEntitySet<E extends Entity> extends Clearable<IEntitySet<E>>, Named {
	
	//default method
	public default IEntitySet<E> addEntities(final Iterable<E> entities) {
		
		entities.forEach(this::addEntity);
		
		return this;
	}
	
	//abstract method
	public abstract IEntitySet<E> addEntity(E entity);
	
	//default method
	@SuppressWarnings("unchecked")
	public default IEntitySet<E> addEntity(final E... entities) {
		return addEntities(new ReadContainer<>(entities));
	}
	
	//abstract method
	public abstract IEntitySet<E> deleteEntity(E entity);
	
	//abstract method
	public abstract IDatabaseAdapter getParentDatabaseAdapter();
	
	//abstract method
	public abstract IContainer<E> getRefEntities();
	
	//abstract method
	public abstract E getRefEntityById(long id);
	
	//abstract method
	public abstract boolean hasChanges();
		
	//abstract method
	public abstract boolean references(Entity entity);
	
	//abstract method
	public abstract boolean references(IElementTakerBooleanGetter<Entity> selector);
	
	//abstract method
	public abstract void noteMutatedEntity(E entity);
}
