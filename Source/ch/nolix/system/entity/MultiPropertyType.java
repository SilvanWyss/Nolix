//package declaration
package ch.nolix.system.entity;

//class
public final class MultiPropertyType<V> extends DataPropertyoidType<V> {
	
	//constructor
	public MultiPropertyType(final Class<V> valueClass) {
		super(valueClass);
	}
	
	//method
	@Override
	public final PropertyKind getPropertyKind() {
		return PropertyKind.MULTI_DATA;
	}
}
