//package declaration
package ch.nolix.core.documentNodeDatabaseAdapter;

//own imports
import ch.nolix.core.constants.MultiPascalCaseNameCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.EntityType;
import ch.nolix.core.databaseAdapter.IEntitySetAdapter;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.validator2.Validator;

//class
public final class EntitySetAdapter<E extends Entity>
implements IEntitySetAdapter<E> {

	//attribute
	private final DocumentNodeoid entitySetSpecification;
	
	//package-visible constructor
	EntitySetAdapter(final DocumentNodeoid entitySetSpecification) {
		
		Validator
		.suppose(entitySetSpecification)
		.isInstance();
		
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
		new EntitiesAdapter<E>(
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
