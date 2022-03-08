//package declaration
package ch.nolix.system.objectschema.parametrizedpropertytype;

//own imports
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

//class
public final class ParametrizedOptionalValueType<V> extends BaseParametrizedValueType<V> {
	
	//constructor
	public ParametrizedOptionalValueType(final DataType dataType) {
		super(dataType);
	}
	
	//method
	@Override
	public PropertyType getPropertyType() {
		return PropertyType.OPTIONAL_VALUE;
	}
}
