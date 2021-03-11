//package declaration
package ch.nolix.system.database.parametrizeddatatype;

import ch.nolix.system.database.entity.Entity;

//class
public abstract class BaseParametrizedReferenceType<E extends Entity> extends ParametrizedDataType<E> {
	
	//constructor
	public BaseParametrizedReferenceType(final Class<E> contentClass) {
		super(contentClass);
	}
	
	//method
	public final boolean canReference(final Entity entity) {
		return (getRefContentClass() == entity.getClass());
	}
	
	//method
	public final String getReferencedEntitiesName() {
		return getRefContentClass().getSimpleName();
	}
	
	//method
	@Override
	public final boolean isAnyBackReferenceType() {
		return false;
	}
	
	//method
	@Override
	public final boolean isAnyReferenceType() {
		return true;
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
