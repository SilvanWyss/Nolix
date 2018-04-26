//package delcaration
package ch.nolix.core.specificationDatabaseConnector;

//own imports
import ch.nolix.core.constants.MultiPascalCaseNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.EntityType;
import ch.nolix.core.databaseAdapter.IEntitySetConnector;
import ch.nolix.core.functionInterfaces.IFunction;
import ch.nolix.core.specification.Specification;
import ch.nolix.primitive.validator2.Validator;

//class
public final class EntitySetConnector<E extends Entity>
implements IEntitySetConnector<E, IFunction> {

	//attribute
	private final Specification entitySetSpecification;
	
	//package-visible constructor
	EntitySetConnector(final Specification entitySetSpecification) {
		
		Validator
		.suppose(entitySetSpecification)
		.isNotNull();
		
		this.entitySetSpecification = entitySetSpecification;
	}
	
	//method
	public void add(final E entity) {
		getEntitiesConnector().add(entity);
	}
	
	//method
	public IFunction createCommandForAdd(final E entity) {
		return () -> add(entity);
	}

	//method
	public IFunction createCommandForDelete(final E entity) {
		return () -> delete(entity);
	}

	//method
	public IFunction createCommandForUpdate(final E entity) {
		return () -> update(entity);
	}
	
	//method
	public void delete(final E entity) {
		getEntitiesConnector().delete(entity);
	}
	
	//method
	public EntitiesConnector<E> getEntitiesConnector() {
		return
		new EntitiesConnector<E>(
			entitySetSpecification.getRefFirstAttribute(
				MultiPascalCaseNameCatalogue.ENTITIES
			)
		);
	}
	
	//method
	public List<E> getEntities(final EntityType<E> entityType) {
		return getEntitiesConnector().getEntities(entityType);
	}

	//method
	public E getEntity(final int id, final EntityType<E> entityType) {
		return getEntitiesConnector().getEntity(id, entityType);
	}
	
	//method
	public String getName() {
		return
		entitySetSpecification
		.getRefFirstAttribute()
		.getOneAttributeAsString();
	}
	
	//method
	public void update(final E entity) {
		getEntitiesConnector().update(entity);
	}
}
