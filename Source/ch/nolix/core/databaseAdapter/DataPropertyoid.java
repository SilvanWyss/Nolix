//package declaration
package ch.nolix.core.databaseAdapter;

//abstract class
public abstract class DataPropertyoid<V> extends Propertyoid<V> {
	
	//method
	public final String toString() {
		return internal_getValues().toString();
	}
}
