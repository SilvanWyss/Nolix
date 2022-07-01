//package declaration
package ch.nolix.system.objectdata.data;

import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ISchema;

//class
public final class Schema implements ISchema<DataImplementation> {
	
	//static method
	@SuppressWarnings("unchecked")
	public static Schema withEntityType(final Class<?>... entityTypesInOrder) {
		return new Schema(ReadContainer.forArray((Class<? extends Entity>[])entityTypesInOrder));
	}
	
	//static method
	public static Schema withEntityTypes(final IContainer<Class<? extends Entity>> entityTypesInOrder) {
		return new Schema(entityTypesInOrder);
	}
	
	//multi-attribute
	private final IContainer<Class<? extends Entity>> entityTypesInOrder;
	
	//constructor
	private Schema(final IContainer<Class<? extends Entity>> entityTypesInOrder) {
		
		assertContainsDifferentEntityTypesOnly(entityTypesInOrder);
		
		this.entityTypesInOrder = entityTypesInOrder;
	}
	
	//method	
	@Override
	public IContainer<Class<IEntity<DataImplementation>>> getEntityTypesInOrder() {
		return entityTypesInOrder.asContainerWithElementsOfEvaluatedType();
	}
	
	//method
	private void assertContainsDifferentEntityTypesOnly(final IContainer<Class<? extends Entity>> entityTypes) {
		if (!containsDifferentEntityTypesOnly(entityTypes)) {
			throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
				"list of entity types",
				entityTypes,
				"does not contain different entity types only"
			);
		}
	}
	
	//method
	private boolean containsDifferentEntityTypesOnly(final IContainer<Class<? extends Entity>> entityTypes) {
		
		for (final var et : entityTypes) {
			if (entityTypes.getCount(et2 -> et2.getSimpleName().equals(et.getSimpleName())) > 1) {
				return false;
			}
		}
		
		return true;
	}
}
