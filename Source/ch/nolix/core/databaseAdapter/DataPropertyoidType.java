//package declaration
package ch.nolix.core.databaseAdapter;

//abstract class
public abstract class DataPropertyoidType<V>
extends PropertyoidType<V> {
	
	//constructor
	public DataPropertyoidType(final Class<V> valueClass) {
		super(valueClass);
	}
	
	//method
	public final boolean captionsPropertyThatCanReference(final Entity entity) {
		return false;
	}

	//method
	public final PropertyKind getPropertyKind() {
		return PropertyKind.DATA;
	}
}
