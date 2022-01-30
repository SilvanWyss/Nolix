//package declaration
package ch.nolix.system.database.databaseadapter;

import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.database.entity.Entity;

//class
public abstract class Schema {

	//multi-attribute
	private final LinkedList<EntityType<Entity>> entityTypes = new LinkedList<>();
	
	//constructor
	@SuppressWarnings("unchecked")
	public Schema(final Iterable<Class<?>> entityClasses) {
		for (final var ec : entityClasses) {
			addEntityType(new EntityType<Entity>((Class<Entity>)ec));
		}
	}
	
	//constructor
	public Schema(final Class<?>... entityClasses) {
		this(ReadContainer.forArray(entityClasses));
	}
	
	//method
	public boolean containsEntityType(final String name) {
		return entityTypes.containsAny(et -> et.hasName(name));
	}
	
	//method
	public EntityType<Entity> getRefEntityTypeByName(final String name) {
		return entityTypes.getRefFirst(et -> et.hasName(name));
	}

	//method
	public ReadContainer<EntityType<Entity>> getRefEntityTypes() {
		return ReadContainer.forIterable(entityTypes);
	}
	
	//method
	public IContainer<ch.nolix.system.objectschema.databaseschemaadapter.EntitySet> getSchemaEntitySets() {
		
		final var schemaEntitySets = entityTypes.to(EntityType::toEmptySchemaEntitySet);
		entityTypes.forEach(et -> et.fillUpColumnsInOwnSchemaEntitySetFrom(schemaEntitySets));
		
		return schemaEntitySets;
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
			new InvalidArgumentException(
				this,
				"contains already an entity type '" + name + "'"
			);
		}
	}
}
