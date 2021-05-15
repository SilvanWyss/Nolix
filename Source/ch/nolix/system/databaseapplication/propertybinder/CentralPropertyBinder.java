//package declaration
package ch.nolix.system.databaseapplication.propertybinder;

//own imports
import ch.nolix.system.database.entity.Property;

//class
public final class CentralPropertyBinder {
	
	//static attribute
	private static final PropertyBinderFactory propertyBinderFactory = new PropertyBinderFactory();
	
	//static method
	public static <P extends Property<?>> PropertyBinding createWidgetAndBindItWith(final P property) {
		return propertyBinderFactory.getPropertyBinderFor(property).createWidgetAndBindItWith(property);
	}
	
	//constructor
	private CentralPropertyBinder() {}
}
