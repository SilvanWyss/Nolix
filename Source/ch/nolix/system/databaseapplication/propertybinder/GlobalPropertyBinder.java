//package declaration
package ch.nolix.system.databaseapplication.propertybinder;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;

//class
public final class GlobalPropertyBinder {
	
	//static attribute
	private static final PropertyBinderFactory propertyBinderFactory = new PropertyBinderFactory();
	
	//static method
	public static <P extends IProperty<?>> PropertyBinding createControlAndBindItWith(final P property) {
		return propertyBinderFactory.getPropertyBinderFor(property).createControlAndBindItWith(property);
	}
	
	//constructor
	private GlobalPropertyBinder() {}
}
