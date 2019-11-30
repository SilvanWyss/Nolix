//package declaration
package ch.nolix.system.entity;

//class
public final class MultiValuePropertyType<V> extends BaseValuePropertyType<V> {
	
	//constructor
	public MultiValuePropertyType(final Class<V> valueClass) {
		super(valueClass);
	}
	
	//method
	@Override
	public final PropertyCategory getPropertyKind() {
		return PropertyCategory.MULTI_VALUE;
	}
}
