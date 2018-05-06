//package declaration
package ch.nolix.core.databaseAdapter;

//own imports
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.SingleReferenceProperty;
import ch.nolix.core.interfaces.Clearable;

//class
public final class OptionalReferenceProperty<E extends Entity>
extends SingleReferenceProperty<E>
implements Clearable<OptionalReferenceProperty<E>> {

	//method
	public OptionalReferenceProperty<E> clear() {
		
		internal_clear();
		
		return this;
	}
	
	//method
	public OptionalPropertyType<E> getPropertyType() {
		return new OptionalPropertyType<>(getValueClass());
	}
	
	//method
	public boolean isOptional() {
		return true;
	}
}
