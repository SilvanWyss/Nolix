//package declaration
package ch.nolix.system.databaseAdapter;

import ch.nolix.common.constant.MultiVariableNameCatalogue;
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.LinkedList;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.valueCreator.ValueCreator;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.IDatabaseAdapter;
import ch.nolix.system.entity.IEntitySet;

//class
public abstract class DatabaseAdapter implements IDatabaseAdapter {
	
	//attributes
	private final Schema schema;
	private final ValueCreator<BaseNode> valueCreator = new ValueCreator<>();
	
	//multi-attributes
	private final LinkedList<EntitySet<Entity>> entitySets = new LinkedList<>();
	private final LinkedList<Entity> mutatedEntitiesInOrder = new LinkedList<>();
	
	//constructor
	public DatabaseAdapter(final Schema schema) {
		
		Validator.assertThat(schema).isOfType(Schema.class);
		
		this.schema = schema;
				
		valueCreator
		.registerSpecificValueCreator(ch.nolix.common.templates.FromNodeSpecificValueCreatorCatalogue.BIG_DECIMAL_CREATOR)
		.registerSpecificValueCreator(ch.nolix.common.templates.FromNodeSpecificValueCreatorCatalogue.BINARY_OBJECT_CREATOR)
		.registerSpecificValueCreator(ch.nolix.common.templates.FromNodeSpecificValueCreatorCatalogue.BOOLEAN_CREATOR)
		.registerSpecificValueCreator(ch.nolix.common.templates.FromNodeSpecificValueCreatorCatalogue.INTEGER_CREATOR)
		.registerSpecificValueCreator(ch.nolix.common.templates.FromNodeSpecificValueCreatorCatalogue.STRING_CREATOR)
		.registerSpecificValueCreator(ch.nolix.element.templates.FromNodeSpecificValueCreatorCatalogue.IMAGE_CREATOR);
		
		reset();
	}
	
	//method
	public final DatabaseAdapter addEntity(final Entity entity) {
				
		@SuppressWarnings("unchecked")
		final var entitySet = (EntitySet<Entity>)getRefEntitySet(entity.getClass());
		
		entitySet.addEntity(entity);
		
		return this;
	}
	
	//method
	public final DatabaseAdapter addEntity(final Entity... entities) {
		
		Validator.assertThat(entities).thatIsNamed(MultiVariableNameCatalogue.ENTITIES).isNotNull();
		
		for (final var e : entities) {
			addEntity(e);
		}
		
		return this;
	}
	
	//method declaration
	public abstract DatabaseAdapter createNewDatabaseAdapter();
	
	//method
	@Override
	public final <V> V createValueFromSpecification(final Class<V> type, final BaseNode specificaiton) {
		return valueCreator.ofType(type).createFrom(specificaiton);
	}

	//method
	public final boolean containsEntitySet(final String name) {
		return entitySets.contains(es -> es.hasName(name));
	}
	
	//method declaration
	public abstract String getDatabaseName();
	
	//method
	public final <E extends Entity> IContainer<E> getRefEntities(final Class<E> entityClass) {
		return getRefEntitySet(entityClass).getRefEntities();
	}
	
	//method
	@Override
	@SuppressWarnings("unchecked")
	public final <E extends Entity> EntitySet<E> getRefEntitySet(final Class<E> entityClass) {
		return
		(EntitySet<E>)entitySets.getRefFirst(es -> es.hasName(entityClass.getSimpleName()));
	}
	
	//method
	public final EntitySet<Entity> getRefEntitySet(final String name) {
		return entitySets.getRefFirst(es -> es.hasName(name));
	}
	
	//method
	@SuppressWarnings("unchecked")
	public final IContainer<EntitySet<Entity>> getRefEntitySets() {
		return entitySets;
	}
	
	//method
	@Override
	public final boolean hasChanges() {
		return mutatedEntitiesInOrder.containsAny();
	}
	
	//method
	@Override
	public final DatabaseAdapter reset() {
		
		entitySets.clear();
		mutatedEntitiesInOrder.clear();
		
		for (final var et : schema.getRefEntityTypes()) {
			entitySets.addAtEnd(EntitySet.createEntitySet(this, et));
		}
		
		return this;
	}
	
	//method
	@Override
	public final void saveChanges() {
		
		saveChangesToDatabase(mutatedEntitiesInOrder);
		
		reset();
	}
	
	//method
	final void noteMutatedEntity(final Entity entity) {
		if (!mutatedEntitiesInOrder.contains(entity)) {
			mutatedEntitiesInOrder.addAtEnd(entity);
		}
	}
	
	//method declaration
	protected abstract <E extends Entity, ES extends IEntitySet<E>> BaseEntitySetAdapter<E> getEntitySetAdapter(
		ES entitySet
	);
	
	//method
	protected final Schema getSchema() {
		return schema;
	}
	
	//method
	protected final ValueCreator<BaseNode> getValueCreator() {
		return valueCreator;
	}
	
	//method declaration
	protected abstract void saveChangesToDatabase(IContainer<Entity> mutatedEntitiesInOrder);
}
