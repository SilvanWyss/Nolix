//package delcaration
package ch.nolix.core.specificationDatabaseAdapter;

//own imports
import ch.nolix.core.constants.MultiPascalCaseNameCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.EntityType;
import ch.nolix.core.databaseAdapter.IEntitySetConnector;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.validator2.Validator;

//class
public final class EntitySetConnector<E extends Entity>
implements IEntitySetConnector<E> {

	//attribute
	private final DocumentNodeoid entitySetSpecification;
	
	//package-visible constructor
	EntitySetConnector(final DocumentNodeoid entitySetSpecification) {
		
		Validator
		.suppose(entitySetSpecification)
		.isInstance();
		
		this.entitySetSpecification = entitySetSpecification;
	}
	
	//method
	public void add(final E entity) {
		getEntitiesConnector().add(entity);
	}
	
	//method
	public boolean containsEntity(final long id) {
		return getEntitiesConnector().containsEntity(id);
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
	public E getEntity(final long id, final EntityType<E> entityType) {
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
