//package declaration
package ch.nolix.system.dataTypes;

//own import
import ch.nolix.system.entity.PropertyKind;

//class
public final class OptionalValueType<C> extends BaseValueType<C> {
	
	//constructor
	public OptionalValueType(final Class<C> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.OPTIONAL_VALUE;
	}
}
