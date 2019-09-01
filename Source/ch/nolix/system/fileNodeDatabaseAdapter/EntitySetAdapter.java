//package declaration
package ch.nolix.system.fileNodeDatabaseAdapter;

import ch.nolix.common.constants.MultiPascalCaseNameCatalogue;
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.List;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.validator.Validator;
import ch.nolix.system.databaseAdapter.Entity;
import ch.nolix.system.databaseAdapter.EntityType;
import ch.nolix.system.databaseAdapter.IEntitySetAdapter;

//class
public final class EntitySetAdapter<E extends Entity>
implements IEntitySetAdapter<E> {

	//attribute
	private final BaseNode entitySetSpecification;
	
	//package-visible constructor
	EntitySetAdapter(final BaseNode entitySetSpecification) {
		
		Validator
		.suppose(entitySetSpecification)
		.isNotNull();
		
		this.entitySetSpecification = entitySetSpecification;
	}
	
	//method
	public void add(final E entity) {
		getEntitiesAdapter().add(entity);
	}
	
	//method
	@Override
	public boolean containsEntity(final long id) {
		return getEntitiesAdapter().containsEntity(id);
	}
	
	//method
	public void delete(final E entity) {
		getEntitiesAdapter().delete(entity);
	}
	
	//method
	public EntitiesAdapter<E> getEntitiesAdapter() {
		return
		new EntitiesAdapter<>(
			entitySetSpecification.getRefFirstAttribute(
				MultiPascalCaseNameCatalogue.ENTITIES
			)
		);
	}
	
	//method
	@Override
	public List<E> getEntities(final EntityType<E> entityType) {
		return getEntitiesAdapter().getEntities(entityType);
	}
	
	//method
	@Override
	public List<E> getEntities(IContainer<Long> ids, EntityType<E> entityType) {
		
		final var entitiesAdapter = getEntitiesAdapter();
		
		return ids.to(id -> entitiesAdapter.getEntity(id, entityType));
	}
	
	//method
	@Override
	public E getEntity(final long id, final EntityType<E> entityType) {
		return getEntitiesAdapter().getEntity(id, entityType);
	}
	
	//method
	@Override
	public String getName() {
		return
		entitySetSpecification
		.getRefFirstAttribute()
		.getOneAttributeAsString();
	}
	
	//method
	public void update(final E entity) {
		getEntitiesAdapter().update(entity);
	}
}
