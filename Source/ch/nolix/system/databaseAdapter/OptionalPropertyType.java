//package declaration
package ch.nolix.system.databaseAdapter;

//class
public final class OptionalPropertyType<V> extends SinglePropertyType<V> {
	
	//constructor
	public OptionalPropertyType(final Class<V> valueClass) {
		super(valueClass);
	}
	
	//method
	@Override
	public final PropertyKind getPropertyKind() {
		return PropertyKind.OPTIONAL_DATA;
	}
}
