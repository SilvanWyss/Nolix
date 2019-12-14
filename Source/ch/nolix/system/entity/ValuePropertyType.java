//package declaration
package ch.nolix.system.entity;

//class
public final class ValuePropertyType<V> extends SinglePropertyType<V> {
	
	//constructor
	public ValuePropertyType(final Class<V> valueClass) {
		super(valueClass);
	}
	
	//method
	@Override
	public final PropertyKind getPropertyKind() {
		return PropertyKind.VALUE;
	}
}
