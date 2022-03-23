//package declaration
package ch.nolix.system.objectdata.parametrizedpropertytype;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseParametrizedValueType;

//class
public abstract class BaseParametrizedValueType<
	IMPL,
	V
>
extends ParametrizedPropertyType<IMPL>
implements IBaseParametrizedValueType<IMPL, V> {
	
	//attribute
	private final Class<V> valueType;
	
	//constructor
	public BaseParametrizedValueType(final Class<V> valueType) {
		
		Validator.assertThat(valueType).thatIsNamed(LowerCaseCatalogue.VALUE_TYPE).isNotNull();
		
		this.valueType = valueType;
	}
	
	//method
	@Override
	public final Class<V> getValueType() {
		return valueType;
	}
}
