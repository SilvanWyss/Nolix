//package declaration
package ch.nolix.system.objectdata.parametrizedpropertytype;

//own imports
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

//class
public final class ParametrizedValueType<
	IMPL,
	V
>
extends BaseParametrizedValueType<IMPL, V> {
	
	//constructor
	public ParametrizedValueType(final Class<V> valueType) {
		super(valueType);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.VALUE;
	}
}
