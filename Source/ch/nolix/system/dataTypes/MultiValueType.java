//package declaration
package ch.nolix.system.dataTypes;

//own import
import ch.nolix.system.entity.PropertyKind;

//class
public final class MultiValueType<C> extends BaseValueType<C> {
	
	//constructor
	public MultiValueType(final Class<C> contentClass) {
		super(contentClass);
	}
	
	//method
	@Override
	public PropertyKind getPropertyKind() {
		return PropertyKind.MULTI_VALUE;
	}
}
