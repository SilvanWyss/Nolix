//package declaration
package ch.nolix.core.databaseAdapter;

//own imports
import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.SingleReference;
import ch.nolix.core.skillAPI.Clearable;

//class
public final class OptionalReference<E extends Entity>
extends SingleReference<E>
implements Clearable<OptionalReference<E>> {

	//method
	public OptionalReference<E> clear() {
		
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
