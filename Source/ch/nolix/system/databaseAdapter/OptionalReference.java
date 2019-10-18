//package declaration
package ch.nolix.system.databaseAdapter;

//own imports
import ch.nolix.common.containers.List;
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
	
	//method
	@Override
	public void supposeCanBeSaved() {}
	
	//method
	@Override
	protected final List<Object> internal_getValues() {
		
		final var values = new List<Object>();
		
		if (referencesEntity()) {
			values.addAtEnd(getReferencedEntityId());
		}
		
		return values;
	}
}
