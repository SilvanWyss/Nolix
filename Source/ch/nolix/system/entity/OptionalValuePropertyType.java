//package declaration
package ch.nolix.system.entity;

//class
public final class OptionalValuePropertyType<V> extends SinglePropertyType<V> {
	
	//constructor
	public OptionalValuePropertyType(final Class<V> valueClass) {
		super(valueClass);
	}
	
	//method
	@Override
	public final PropertyCategory getPropertyKind() {
		return PropertyCategory.OPTIONAL_VALUE;
	}
}
