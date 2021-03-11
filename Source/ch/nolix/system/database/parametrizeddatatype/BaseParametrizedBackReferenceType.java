//package declaration
package ch.nolix.system.database.parametrizeddatatype;

import ch.nolix.system.database.entity.Entity;

//class
public abstract class BaseParametrizedBackReferenceType<E extends Entity> extends ParametrizedDataType<E> {
	
	//constructor
	public BaseParametrizedBackReferenceType(final Class<E> contentClass) {
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
	public final boolean isAnyControlType() {
		return false;
	}
	
	//method
	@Override
	public final boolean isAnyValueType() {
		return false;
	}
}
