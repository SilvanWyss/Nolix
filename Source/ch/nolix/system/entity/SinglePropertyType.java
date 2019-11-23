//package declaration
package ch.nolix.system.entity;

//abstract class
public abstract class SinglePropertyType<V> extends DataPropertyoidType<V> {
	
	//constructor
	public SinglePropertyType(final Class<V> valueClass) {
		super(valueClass);
	}
}
