//package declaration
package ch.nolix.system.objectdatabase.schema;

//own imports
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.objectdatabase.database.Entity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.schemaapi.ISchema;

//class
public final class Schema implements ISchema {
	
	//static method
	@SuppressWarnings("unchecked")
	public static Schema withEntityType(final Class<?>... entityTypesInOrder) {
		return new Schema(ReadContainer.forArray((Class<? extends Entity>[])entityTypesInOrder));
	}
	
	//static method
	public static Schema withEntityTypes(IContainer<Class<? extends IEntity>> entityTypesInOrder) {
		return new Schema(entityTypesInOrder);
	}
	
	//multi-attribute
	private final IContainer<Class<? extends IEntity>> entityTypesInOrder;
	
	//constructor
	private Schema(final IContainer<Class<? extends IEntity>> entityTypesInOrder) {
		
		assertContainsDifferentEntityTypesOnly(entityTypesInOrder);
		
		this.entityTypesInOrder = entityTypesInOrder;
	}
	
	//method
	@Override
	public Class<? extends IEntity> getEntityTypeByName(final String name) {
		return getEntityTypes().getRefFirst(et -> et.getSimpleName().equals(name));
	}
	
	//method	
	@Override
	public IContainer<Class<? extends IEntity>> getEntityTypes() {
		return entityTypesInOrder;
	}
	
	//method
	private void assertContainsDifferentEntityTypesOnly(
		final IContainer<Class<? extends IEntity>> entityTypes
	) {
		if (!containsDifferentEntityTypesOnly(entityTypes)) {
			throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
				"list of entity types",
				entityTypes,
				"does not contain different entity types only"
			);
		}
	}
	
	//method
	private boolean containsDifferentEntityTypesOnly(
		final IContainer<Class<? extends IEntity>> entityTypes
	) {
		return entityTypes.getRefGroups(Class::getSimpleName).containsAsManyAs(entityTypes);
	}
}
