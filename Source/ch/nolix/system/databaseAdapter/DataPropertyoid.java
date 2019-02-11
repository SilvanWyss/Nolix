//package declaration
package ch.nolix.system.databaseAdapter;

//abstract class
public abstract class DataPropertyoid<V> extends Propertyoid<V> {
	
	//method
	@Override
	public final boolean canReferenceEntity(final Entity entity) {
		return false;
	}
	
	//method
	@Override
	public final boolean references(final Entity entity) {
		return false;
	}
	
	//method
	@Override
	public final String toString() {
		return internal_getValues().toString();
	}
}
