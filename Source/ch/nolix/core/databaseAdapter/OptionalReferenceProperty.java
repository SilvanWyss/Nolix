//package declaration
package ch.nolix.core.databaseAdapter;

import ch.nolix.core.databaseAdapter.Entity;
import ch.nolix.core.databaseAdapter.SingleReferenceProperty;
//own import
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
	public boolean isOptional() {
		return true;
	}
}
