//package declaration
package ch.nolix.system.databaseAdapter;

//class
public final class PropertyType<V> extends SinglePropertyType<V> {
	
	//constructor
	public PropertyType(final Class<V> valueClass) {
		super(valueClass);
	}
	
	//method
	@Override
	public final PropertyKind getPropertyKind() {
		return PropertyKind.DATA;
	}
}
