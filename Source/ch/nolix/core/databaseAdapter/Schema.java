//package declaration
package ch.nolix.core.databaseAdapter;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.invalidStateException.InvalidStateException;

//abstract class
public abstract class Schema {

	//multi-attribute
	private final List<EntityType<Entity>> entityTypes = new List<EntityType<Entity>>();
	
	//constructor
	@SuppressWarnings("unchecked")
	public Schema(final Iterable<Class<?>> entityClasses) {
		for (final var ec : entityClasses) {
			addEntityType(new EntityType<Entity>((Class<Entity>)ec));
		}
	}
	
	//constructor
	public Schema(final Class<?>... entityClasses) {
		this(new ReadContainer<Class<?>>(entityClasses));
	}
	
	//method
	public boolean containsEntityType(final String name) {
		return entityTypes.contains(et -> et.hasName(name));
	}
	
	//method
	public ReadContainer<EntityType<Entity>> getRefEntityTypes() {
		return new ReadContainer<EntityType<Entity>>(entityTypes);
	}
	
	//method
	private void addEntityType(final EntityType<Entity> entityType) {
		
		supposeDoesNotContainEntityType(entityType.getName());
		
		entityTypes.addAtEnd(entityType);
	}
	
	//method
	private void supposeDoesNotContainEntityType(final String name) {		
		if (containsEntityType(name)) {
			throw
			new InvalidStateException(
				this,
				"contains already an entity type '" + name + "'"
			);
		}
	}
}
