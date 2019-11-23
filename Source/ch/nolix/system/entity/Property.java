//package declaration
package ch.nolix.system.entity;

//own import
import ch.nolix.common.invalidArgumentExceptions.EmptyArgumentException;

//class
public final class Property<V> extends SingleProperty<V> {
	
	//method
	@Override
	public PropertyoidType<V> getPropertyType() {
		return new PropertyType<>(getValueClass());
	}
	
	//method
	@Override
	public boolean isOptional() {
		return false;
	}
	
	//method
	@Override
	public void supposeCanBeSaved() {
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
	}
}
