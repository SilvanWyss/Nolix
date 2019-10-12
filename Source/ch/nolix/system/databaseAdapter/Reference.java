//package declaration
package ch.nolix.system.databaseAdapter;

//own import
import ch.nolix.common.containers.List;

//class
public final class Reference<E extends Entity> extends SingleReference<E> {
	
	//method
	@Override
	public ReferenceType<E> getPropertyType() {
		return new ReferenceType<>(getValueClass());
	}
	
	//method
	@Override
	public boolean isOptional() {
		return false;
	}
	
	//method
	@Override
	protected final List<Object> internal_getValues() {
		return new List<>(getReferencedEntityId());
	}
}
