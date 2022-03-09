//package declaration
package ch.nolix.system.databaseapplication.propertybinder;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;

//class
public final class CentralPropertyBinder {
	
	//static attribute
	private static final PropertyBinderFactory propertyBinderFactory = new PropertyBinderFactory();
	
	//static method
	public static <P extends IProperty<?>> PropertyBinding createWidgetAndBindItWith(final P property) {
		return propertyBinderFactory.getPropertyBinderFor(property).createWidgetAndBindItWith(property);
	}
	
	//constructor
	private CentralPropertyBinder() {}
}
