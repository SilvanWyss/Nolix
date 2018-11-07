//package declaration
package ch.nolix.core.databaseAdapter;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.factory.Factory;
import ch.nolix.core.functionAPI.IElementTakerElementGetter;
import ch.nolix.core.skillInterfaces.IChangesSaver;
import ch.nolix.core.validator2.Validator;

//abstract class
public abstract class DatabaseAdapter implements IChangesSaver<DatabaseAdapter> {

	//static attribute
	private static final Factory<DocumentNodeoid, Object> valueFactory =
	new Factory<DocumentNodeoid, Object>()
	.addInstanceCreator(Boolean.class.getSimpleName(), s -> s.toBoolean())
	.addInstanceCreator(Integer.class.getSimpleName(), s -> s.toInt())
	.addInstanceCreator(String.class.getSimpleName(), s -> s.toString());
	
	//static method
	public final static void addValueCreator(
		final String type,
		final IElementTakerElementGetter<DocumentNodeoid, Object> valueCreator
	) {
		valueFactory.addInstanceCreator(type, valueCreator);
	}
	
	//static method
	public final static boolean canCreateValue(final String type) {
		return valueFactory.canCreateInstanceOf(type);
	}
	
	//static method
	public final static Object createValue(
		final String type,
		final DocumentNodeoid input
	) {
		return valueFactory.createInstance(type, input);
	}
	
	//attributes
	private final Schema schema;
	
	//multi-attributes
	private final List<EntitySet<Entity>> entitySets = new List<EntitySet<Entity>>();
	private final List<Entity> changedEntitiesInOrder = new List<Entity>();
		
	//constructor
	public DatabaseAdapter(final Schema schema) {
		
		Validator.suppose(schema).isInstanceOf(Schema.class);		
		
		this.schema = schema;
		
		reset();
	}
	
	//method
	public final boolean containsEntitySet(final String name) {
		return entitySets.contains(es -> es.hasName(name));
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
	public final boolean hasChanges() {
		return changedEntitiesInOrder.containsAny();
	}
	
	//method
	public final DatabaseAdapter reset() {	
		
		entitySets.clear();
		changedEntitiesInOrder.clear();
		
		for (final var et : schema.getRefEntityTypes()) {
			entitySets.addAtEnd(EntitySet.createEntitySet(this, et));
		}
		
		return this;
	}
	
	//method
	public final void saveChanges() {
		
		saveChangesToDatabase(changedEntitiesInOrder);
		
		reset();
	}
	
	//method
	final void noteChangedEntity(final Entity entity) {
		if (!changedEntitiesInOrder.contains(entity)) {
			changedEntitiesInOrder.addAtEnd(entity);
		}
	}
	
	//abstract method
	protected abstract <E extends Entity> IEntitySetConnector<E> getEntitySetConnector(
		EntitySet<E> entitySet
	);
	
	//abstract method
	protected abstract void saveChangesToDatabase(Iterable<Entity> changedEntitiesInOrder);
}
