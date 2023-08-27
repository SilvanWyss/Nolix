//package declaration
package ch.nolix.system.objectdatabase.parametrizedpropertytype;

//own imports
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

//class
public final class ParameterizedOptionalValueType<

	V
>
extends BaseParametrizedValueType<V> {
	
	//constructor
	public ParameterizedOptionalValueType(final Class<V> valueType) {
		super(valueType);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.OPTIONAL_VALUE;
	}
}
