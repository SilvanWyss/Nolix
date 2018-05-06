//package declaration
package ch.nolix.core.databaseAdapter;

//own import
import ch.nolix.core.interfaces.Clearable;

//class
public class OptionalProperty<V>
extends SingleProperty<V>
implements Clearable<OptionalProperty<V>> {

	//method
	public OptionalProperty<V> clear() {
		
		internal_clear();
		
		return this;
	}
	
	//method
	public boolean isOptional() {
		return true;
	}

	//method
	public PropertyoidType<V> getPropertyType() {
		return new OptionalPropertyType<V>(getValueClass());
	}
}
