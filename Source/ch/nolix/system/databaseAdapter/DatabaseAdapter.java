//package declaration
package ch.nolix.system.databaseAdapter;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.functionAPI.IElementTakerElementGetter;
import ch.nolix.core.instanceCreator.InstanceCreator;
import ch.nolix.core.skillAPI.IChangesSaver;
import ch.nolix.core.validator.Validator;

//abstract class
public abstract class DatabaseAdapter implements IChangesSaver<DatabaseAdapter> {
	
	//static attribute
	private static final InstanceCreator<DocumentNodeoid, Object> valueFactory =
	new InstanceCreator<DocumentNodeoid, Object>()
	.addInstanceCreator(Boolean.class.getSimpleName(), s -> s.toBoolean())
	.addInstanceCreator(Integer.class.getSimpleName(), s -> s.toInt())
	.addInstanceCreator(String.class.getSimpleName(), s -> s.toString());
	
	//static method
	public static final void addValueCreator(
		final String type,
		final IElementTakerElementGetter<DocumentNodeoid, Object> valueCreator
	) {
		valueFactory.addInstanceCreator(type, valueCreator);
	}
	
	//static method
	public static final boolean canCreateValue(final String type) {
		return valueFactory.canCreateInstanceOf(type);
	}
	
	//static method
	public static final Object createValue(
		final String type,
		final DocumentNodeoid input
	) {
		return valueFactory.createInstance(type, input);
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
	public final boolean containsEntitySet(final String name) {
		return entitySets.contains(es -> es.hasName(name));
	}
	
	//abstract method
	public abstract String getDatabaseName();
	
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
