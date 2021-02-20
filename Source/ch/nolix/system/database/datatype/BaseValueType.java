//package declaration
package ch.nolix.system.database.datatype;

import ch.nolix.system.database.entity.Entity;

//class
public abstract class BaseValueType<C> extends DataType<C> {
	
	//constructor
	public BaseValueType(final Class<C> contentClass) {
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
	public final boolean isAnyTechnicalType() {
		return false;
	}
	
	//method
	@Override
	public final boolean isAnyValueType() {
		return true;
	}
}
