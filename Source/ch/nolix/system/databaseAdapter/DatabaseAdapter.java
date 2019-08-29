//package declaration
package ch.nolix.system.databaseAdapter;

//own imports
import ch.nolix.core.constants.MultiVariableNameCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.containers.IContainer;
import ch.nolix.core.containers.List;
import ch.nolix.core.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.core.node.BaseNode;
import ch.nolix.core.skillAPI.IChangesSaver;
import ch.nolix.core.validator.Validator;

//abstract class
public abstract class DatabaseAdapter implements IChangesSaver<DatabaseAdapter> {
	
	//static method
	public static final Object createValue(
		final String type,
		final BaseNode input
	) {
		switch (type) {
			case "Boolean":
				return input.toBoolean();
			case "Integer":
				return input.toInt();
			case "String":
				return input.toString();
			default:
				throw new InvalidArgumentException(VariableNameCatalogue.TYPE, type, "is not known");
		}
	}
	
	//attributes
	private final Schema schema;
	
	//multi-attributes
	private final List<EntitySet<Entity>> entitySets = new List<>();
	private final List<Entity> mutatedEntitiesInOrder = new List<>();
	
	//constructor
	public DatabaseAdapter(final Schema schema) {
		
		Validator.suppose(schema).isOfType(Schema.class);
		
		this.schema = schema;
		
		reset();
	}
	
	//method
	@SuppressWarnings("unchecked")
	public final <E extends Entity> DatabaseAdapter addEntity(final E... entities) {
		
		Validator.suppose(entities).thatIsNamed(MultiVariableNameCatalogue.ENTITIES).isNotNull();
		
		if (entities.length > 0) {
			final var entitySet = (EntitySet<E>)getRefEntitySet(entities[0].getClass());
			entitySet.addEntity(entities);
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
	
	//abstract method
	protected abstract <E extends Entity> IEntitySetAdapter<E> getEntitySetAdapter(
		EntitySet<E> entitySet
	);
	
	//abstract method
	protected abstract void saveChangesToDatabase(IContainer<Entity> mutatedEntitiesInOrder);
}
