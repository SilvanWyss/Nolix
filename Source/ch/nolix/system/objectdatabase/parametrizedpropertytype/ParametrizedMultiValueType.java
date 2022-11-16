//package declaration
package ch.nolix.system.objectdatabase.parametrizedpropertytype;

//own imports
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

//class
public final class ParametrizedMultiValueType<
	IMPL,
	V
>
extends BaseParametrizedValueType<IMPL, V> {
	
	//constructor
	public ParametrizedMultiValueType(final Class<V> valueType) {
		super(valueType);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.MULTI_VALUE;
	}
}
