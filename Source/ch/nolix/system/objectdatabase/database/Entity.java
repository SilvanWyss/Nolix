//package declaration
package ch.nolix.system.objectdatabase.database;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//class
public abstract class Entity extends BaseEntity {
	
	//static attribute
	private static final PropertyFromEntityExtractor propertyFromEntityExtractor = new PropertyFromEntityExtractor();
	
	//method
	@Override
	public final String getParentTableName() {
		return getClass().getSimpleName();
	}
	
	//method
	@Override
	final IContainer<Property> internalLoadProperties() {
		return propertyFromEntityExtractor.getOriPropertiesFrom(this);
	}
}
