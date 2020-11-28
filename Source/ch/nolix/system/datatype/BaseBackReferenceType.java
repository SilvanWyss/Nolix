//package declaration
package ch.nolix.system.datatype;

//own import
import ch.nolix.system.entity.Entity;

//class
public abstract class BaseBackReferenceType<E extends Entity> extends DataType<E> {
	
	//constructor
	public BaseBackReferenceType(final Class<E> contentClass) {
		super(contentClass);
	}
	
	//method
	public final boolean canReference(final Entity entity) {
		return false;
	}
	
	//method
	public final String getBackReferencedEntitiesName() {
		return getRefContentClass().getSimpleName();
	}
	
	//method
	@Override
	public final boolean isAnyBackReferenceType() {
		return true;
	}
	
	//method
	@Override
	public final boolean isAnyReferenceType() {
		return false;
	}
	
	//method
	@Override
	public final boolean isAnyTechnicalType() {
		return false;
	}
	
	//method
	@Override
	public final boolean isAnyValueType() {
		return false;
	}
}
