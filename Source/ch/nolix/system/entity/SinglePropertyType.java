//package declaration
package ch.nolix.system.entity;

//abstract class
public abstract class SinglePropertyType<V> extends BaseValuePropertyType<V> {
	
	//constructor
	public SinglePropertyType(final Class<V> valueClass) {
		super(valueClass);
	}
}
