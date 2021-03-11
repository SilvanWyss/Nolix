//package declaration
package ch.nolix.system.database.parametrizeddatatype;

import ch.nolix.system.database.entity.Entity;

//class
public abstract class BaseParametrizedControlType<C> extends ParametrizedDataType<C> {
	
	//constructor
	public BaseParametrizedControlType(final Class<C> contentClass) {
		super(contentClass);
	}
	
	//method
	public final boolean canReference(final Entity entity) {
		return false;
	}
	
	//method
	@Override
	public boolean isAnyBackReferenceType() {
		return false;
	}
	
	//method
	@Override
	public boolean isAnyReferenceType() {
		return false;
	}
	
	//method
	@Override
	public boolean isAnyControlType() {
		return true;
	}
	
	//method
	@Override
	public boolean isAnyValueType() {
		return false;
	}
}
