//package declaration
package ch.nolix.system.database.databaseadapter;

//own imports
import ch.nolix.common.constant.PluralLowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.valuecreator.ValueCreator;
import ch.nolix.system.database.databaseschemaadapter.DatabaseSchemaAdapter;
import ch.nolix.system.database.entity.Entity;
import ch.nolix.system.database.entity.IDatabaseAdapter;
import ch.nolix.system.database.entity.IEntitySet;

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
		
		Validator.assertThat(schema).thatIsNamed(Schema.class).isNotNull();
		
		this.schema = schema;
				
		valueCreator
		.registerSpecificValueCreator(ch.nolix.common.template.ValueFromNodeCreatorCatalogue.BIG_DECIMAL_CREATOR)
		.registerSpecificValueCreator(ch.nolix.common.template.ValueFromNodeCreatorCatalogue.BINARY_OBJECT_CREATOR)
		.registerSpecificValueCreator(ch.nolix.common.template.ValueFromNodeCreatorCatalogue.BOOLEAN_CREATOR)
		.registerSpecificValueCreator(ch.nolix.common.template.ValueFromNodeCreatorCatalogue.INTEGER_CREATOR)
		.registerSpecificValueCreator(ch.nolix.common.template.ValueFromNodeCreatorCatalogue.STRING_CREATOR)
		.registerSpecificValueCreator(ch.nolix.element.template.FromNodeCreatorCatalogue.IMAGE_CREATOR);
		
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
		
		Validator.assertThat(entities).thatIsNamed(PluralLowerCaseCatalogue.ENTITIES).isNotNull();
		
		for (final var e : entities) {
			addEntity(e);
		}
		
		return this;
	}
	
	//method declaration
	public abstract DatabaseAdapter createNewDatabaseAdapter();
	
	//method declaration
	public abstract DatabaseSchemaAdapter<?> createDatabaseSchemaAdapter();
	
	//method
	@Override
	public final <V> V createValueFromSpecification(final Class<V> type, final BaseNode specificaiton) {
		return valueCreator.ofType(type).createFrom(specificaiton);
	}

	//method
	public final boolean containsEntitySet(final String name) {
		return entitySets.contains(es -> es.hasName(name));
	}
	
	//method
	public final boolean databaseIsEmpty() {
		return getRefEntitySets().containsNone(EntitySet::containsAny);
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
	public final void reset() {
		
		entitySets.clear();
		mutatedEntitiesInOrder.clear();
		
		for (final var et : schema.getRefEntityTypes()) {
			entitySets.addAtEnd(EntitySet.createEntitySet(this, et));
		}
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
	
	//method
	protected final void addSchemaIfDatabaseIsEmpty(final Schema schema) {
		
		final var databaseSchemaAdapter = createDatabaseSchemaAdapter();
		
		if (!databaseSchemaAdapter.containsEntitySet()) {
			databaseSchemaAdapter.addEntitySets(schema.getSchemaEntitySets());
			databaseSchemaAdapter.saveChanges();
		}
	}
	
	//method declaration
	protected abstract void saveChangesToDatabase(IContainer<Entity> mutatedEntitiesInOrder);
}
