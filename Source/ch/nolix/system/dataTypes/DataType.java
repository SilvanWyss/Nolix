//package declaration
package ch.nolix.system.dataTypes;

//own imports
import ch.nolix.common.validator.Validator;
import ch.nolix.system.entity.Entity;
import ch.nolix.system.entity.PropertyKind;

//class
public abstract class DataType<C> {
	
	//attribute
	private final Class<C> contentClass;
	
	//constructor
	public DataType(final Class<C> contentClass) {
		
		Validator.suppose(contentClass).thatIsNamed("content class").isNotNull();
		
		this.contentClass = contentClass;
	}
	
	//method
	public abstract boolean canReference(Entity entity);
	
	//method
	public abstract PropertyKind getPropertyKind();
	
	//method
	public final Class<C> getRefContentClass() {
		return contentClass;
	}
	
	//method
	public abstract boolean isAnyBackReferenceType();
	
	//method
	public abstract boolean isAnyReferenceType();
	
	//method
	public abstract boolean isAnyTechnicalType();
	
	//method
	public abstract boolean isAnyValueType();
}
