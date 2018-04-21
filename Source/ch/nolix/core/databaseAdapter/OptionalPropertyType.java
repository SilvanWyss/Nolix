//package declaration
package ch.nolix.core.databaseAdapter;

//class
public final class OptionalPropertyType<V> extends SinglePropertyType<V> {

	//constructor
	public OptionalPropertyType(final Class<V> valueClass) {
		super(valueClass);
	}
}
