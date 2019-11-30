//package declaration
package ch.nolix.system.entity;

//abstract class
public abstract class BaseValuePropertyType<V>
extends PropertyType<V> {
	
	//constructor
	public BaseValuePropertyType(final Class<V> valueClass) {
		super(valueClass);
	}
	
	//method
	@Override
	public final boolean captionsPropertyThatCanReference(final Entity entity) {
		return false;
	}
	
	//method
	@Override
	public boolean referencesEntitySet(final String name) {
		return false;
	}
}
