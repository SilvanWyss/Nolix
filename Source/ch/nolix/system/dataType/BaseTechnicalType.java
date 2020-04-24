//package declaration
package ch.nolix.system.dataType;

//own imports
import ch.nolix.system.entity.Entity;

//class
public abstract class BaseTechnicalType<C> extends DataType<C> {
	
	//constructor
	public BaseTechnicalType(final Class<C> contentClass) {
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
	public boolean isAnyTechnicalType() {
		return true;
	}
	
	//method
	@Override
	public boolean isAnyValueType() {
		return false;
	}
}
