//package declaration
package ch.nolix.core.databaseAdapter;

//own import
import ch.nolix.core.interfaces.Clearable;

//class
public class OptionalProperty<V>
extends SingleProperty<V>
implements Clearable<OptionalProperty<V>> {

	//method
	public boolean isOptional() {
		return true;
	}

	//method
	public OptionalProperty<V> clear() {
		return this;
	}

	//method
	public PropertyoidType<V> getPropertyType() {
		return new OptionalPropertyType<V>(getValueClass());
	}
}
