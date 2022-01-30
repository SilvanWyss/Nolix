//package declaration
package ch.nolix.system.database.entity;

import ch.nolix.core.attributeapi.mandatoryattributeapi.Named;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.skillapi.Clearable;

//interface
public interface IEntitySet<E extends Entity> extends Clearable, Named {
	
	//method
	default IEntitySet<E> addEntities(final Iterable<E> entities) {
		
		entities.forEach(this::addEntity);
		
		return this;
	}
	
	//method declaration
	IEntitySet<E> addEntity(E entity);
	
	//method
	@SuppressWarnings("unchecked")
	default IEntitySet<E> addEntity(final E... entities) {
		return addEntities(ReadContainer.forArray(entities));
	}
	
	//method declaration
	<E2 extends Entity> boolean canReferenceBackEntityOfType(final Class<E2> type);
	
	//method declaration
	<E2 extends Entity> boolean canReferenceEntityOfType(final Class<E2> type);
	
	//method declaration
	IEntitySet<E> deleteEntity(E entity);
	
	//method declaration
	IDatabaseAdapter getParentDatabaseAdapter();
	
	//method declaration
	IContainer<E> getRefEntities();
	
	//method declaration
	E getRefEntityById(long id);
	
	//method
	default E getRefEntityById(final String id) {
		return getRefEntityById(Long.valueOf(id));
	}
	
	//method declaration
	boolean hasChanges();
	
	//method declaration
	boolean references(Entity entity);
	
	//method declaration
	void noteMutatedEntity(E entity);
}
