//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ISchema;

//class
public final class Schema implements ISchema<DataImplementation> {
	
	//static method
	@SuppressWarnings("unchecked")
	public static Schema withEntityType(final Class<Entity>... entityTypesInOrder) {
		return new Schema(ReadContainer.forArray(entityTypesInOrder));
	}
	
	//static method
	public static Schema withEntityTypes(final IContainer<Class<Entity>> entityTypesInOrder) {
		return new Schema(entityTypesInOrder);
	}
	
	//multi-attribute
	private final IContainer<Class<Entity>> entityTypesInOrder;
	
	//constructor
	private Schema(final IContainer<Class<Entity>> entityTypesInOrder) {
		
		assertContainsDifferentEntityTypesOnly(entityTypesInOrder);
		
		this.entityTypesInOrder = entityTypesInOrder.toList();
	}
	
	//method	
	@Override
	public IContainer<Class<IEntity<DataImplementation>>> getEntityTypesInOrder() {
		return entityTypesInOrder.asContainerWithElementsOfEvaluatedType();
	}
	
	//method
	private void assertContainsDifferentEntityTypesOnly(final IContainer<Class<Entity>> entityTypes) {
		if (!containsDifferentEntityTypesOnly(entityTypes)) {
			throw new InvalidArgumentException(
				"list of entity types",
				entityTypes,
				"does not contain different entity types only"
			);
		}
	}
	
	//method
	private boolean containsDifferentEntityTypesOnly(final IContainer<Class<Entity>> entityTypes) {
		
		for (final var et : entityTypes) {
			if (entityTypes.containsOne(et2 -> et2.getSimpleName().equals(et.getSimpleName()))) {
				return false;
			}
		}
		
		return true;
	}
}
