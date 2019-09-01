//package declaration
package ch.nolix.system.databaseAdapter;

import ch.nolix.common.skillAPI.Clearable;

//class
public final class OptionalReference<E extends Entity>
extends SingleReference<E>
implements Clearable<OptionalReference<E>> {

	//method
	@Override
	public OptionalReference<E> clear() {
		
		internal_clear();
		
		return this;
	}
	
	//method
	@Override
	public OptionalPropertyType<E> getPropertyType() {
		return new OptionalPropertyType<>(getValueClass());
	}
	
	//method
	@Override
	public boolean isOptional() {
		return true;
	}
}
