//package declaration
package ch.nolix.system.fileNodeDatabaseAdapter;

//own imports
import ch.nolix.common.constants.MultiPascalCaseNameCatalogue;
import ch.nolix.common.containers.List;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.valueCreator.ValueCreator;
import ch.nolix.system.databaseAdapter.Entity;
import ch.nolix.system.databaseAdapter.EntityType;
import ch.nolix.system.databaseAdapter.BaseEntitySetAdapter;

//class
public final class EntitySetAdapter<E extends Entity> extends BaseEntitySetAdapter<E> {

	//attribute
	private final BaseNode entitySetSpecification;
	
	//package-visible constructor
	EntitySetAdapter(
		final BaseNode entitySetSpecification,
		final EntityType<E> entityType,
		final ValueCreator valueCreator
	) {
		
		super(entityType, valueCreator);
		
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
			entitySetSpecification.getRefFirstAttribute(MultiPascalCaseNameCatalogue.ENTITIES),
			getValueCreator()
		);
	}
	
	//method
	@Override
	public List<E> getEntities() {
		return getEntitiesAdapter().getEntities(getEntityType());
	}
	
	//method
	@Override
	public E getEntity(final long id) {
		return getEntitiesAdapter().getEntity(id, getEntityType());
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
