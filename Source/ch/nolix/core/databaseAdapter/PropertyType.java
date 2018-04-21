//package declaration
package ch.nolix.core.databaseAdapter;

//class
public final class PropertyType<V>
extends SinglePropertyType<V> {

	//constructor
	public PropertyType(final Class<V> valueClass) {
		super(valueClass);
	}
}
