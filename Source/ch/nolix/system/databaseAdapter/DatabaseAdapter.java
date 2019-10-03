//package declaration
package ch.nolix.system.databaseAdapter;

//own imports
import ch.nolix.common.constants.MultiVariableNameCatalogue;
import ch.nolix.common.containers.IContainer;
import ch.nolix.common.containers.List;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.skillAPI.IChangesSaver;
import ch.nolix.common.validator.Validator;
import ch.nolix.common.valueCreator.SpecificValueCreatorCatalogue;
import ch.nolix.common.valueCreator.ValueCreator;

//abstract class
public abstract class DatabaseAdapter implements IChangesSaver<DatabaseAdapter> {
	
	//attributes
	private final Schema schema;
	private final ValueCreator valueCreator = new ValueCreator();
	
	//multi-attributes
	private final List<EntitySet<Entity>> entitySets = new List<>();
	private final List<Entity> mutatedEntitiesInOrder = new List<>();
	
	//constructor
	public DatabaseAdapter(final Schema schema) {
		
		Validator.suppose(schema).isOfType(Schema.class);
		
		this.schema = schema;
		
		valueCreator.registerSpecificValueCreator(
			SpecificValueCreatorCatalogue.BIG_DECIMAL_CREATOR,
			SpecificValueCreatorCatalogue.BOOLEAN_CREATOR,
			SpecificValueCreatorCatalogue.INTEGER_CREATOR,
			SpecificValueCreatorCatalogue.STRING_CREATOR
		);
		
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
		
		Validator.suppose(entities).thatIsNamed(MultiVariableNameCatalogue.ENTITIES).isNotNull();
		
		for (final var e : entities) {
			addEntity(e);
		}
		
		return this;
	}
	
	//method
	public final boolean containsEntitySet(final String name) {
		return entitySets.contains(es -> es.hasName(name));
	}
	
	//abstract method
	public abstract String getDatabaseName();
	
	//method
	public final <E extends Entity> IContainer<E> getRefEntities(final Class<E> entityClass) {
		return getRefEntitySet(entityClass).getRefEntities();
	}
	
	//method
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
	
	//package-visible method
	final void noteMutatedEntity(final Entity entity) {
		if (!mutatedEntitiesInOrder.contains(entity)) {
			mutatedEntitiesInOrder.addAtEnd(entity);
		}
	}
	
	//method
	protected final <V> V createValueFromSpecification(final Class<V> type, final BaseNode specificaiton) {
		return valueCreator.ofType(type).createFromSpecification(specificaiton);
	}
	
	//method
	protected final <V> V createValueFromString(final Class<V> type, final String string) {
		return valueCreator.ofType(type).createFromString(string);
	}
	
	//abstract method
	protected abstract <E extends Entity> BaseEntitySetAdapter<E> getEntitySetAdapter(
		EntitySet<E> entitySet
	);
	
	//method
	protected final ValueCreator getValueCreator() {
		return valueCreator;
	}
	
	//abstract method
	protected abstract void saveChangesToDatabase(IContainer<Entity> mutatedEntitiesInOrder);
}
