//package declaration
package ch.nolix.system.dataType;

//own import
import ch.nolix.system.entity.PropertyKind;

//class
public final class ValueType<C> extends BaseValueType<C> {
	
	//constructor
	public ValueType(final Class<C> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.VALUE;
	}
}
