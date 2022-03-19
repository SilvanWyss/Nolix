//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.container.IContainer;

//class
public abstract class Entity extends BaseEntity {
	
	//static attribute
	private static final PropertyExtractor propertyExtractor = new PropertyExtractor();
	
	//method
	@Override
	public final String getParentTableName() {
		return getClass().getSimpleName();
	}
	
	//method
	@Override
	final IContainer<Property> internalLoadProperties() {
		return propertyExtractor.getRefPropertiesFrom(this);
	}
}
