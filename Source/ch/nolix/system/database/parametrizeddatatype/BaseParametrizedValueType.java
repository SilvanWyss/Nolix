//package declaration
package ch.nolix.system.database.parametrizeddatatype;

import ch.nolix.system.database.entity.Entity;

//class
public abstract class BaseParametrizedValueType<C> extends ParametrizedDataType<C> {
	
	//constructor
	public BaseParametrizedValueType(final Class<C> contentClass) {
		super(contentClass);
	}
	
	//method
	public final boolean canReference(final Entity entity) {
		return false;
	}
	
	//method
	@Override
	public final boolean isAnyBackReferenceType() {
		return false;
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
		return true;
	}
}
