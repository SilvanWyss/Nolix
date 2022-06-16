//package declaration
package ch.nolix.system.objectdata.parametrizedpropertytype;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.name.LowerCaseCatalogue;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseParametrizedBackReferenceType;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseParametrizedReferenceType;
import ch.nolix.systemapi.objectdataapi.dataapi.IBaseParametrizedValueType;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

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
		
		GlobalValidator.assertThat(valueType).thatIsNamed(LowerCaseCatalogue.VALUE_TYPE).isNotNull();
		
		this.valueType = valueType;
	}
	
	//method
	@Override
	public final IBaseParametrizedBackReferenceType<IMPL, ?> asBaseParametrizedBackReferenceType() {
		throw new ArgumentDoesNotSupportMethodException(this, "asBaseParametrizedBackReferenceType");
	}
	
	//method
	@Override
	public final IBaseParametrizedReferenceType<IMPL, ?> asBaseParametrizedReferenceType() {
		throw new ArgumentDoesNotSupportMethodException(this, "asBaseParametrizedReferenceType");
	}
	
	//method
	@Override
	public final IBaseParametrizedValueType<IMPL, ?> asBaseParametrizedValueType() {
		return this;
	}
	
	//method
	@Override
	public final Class<V> getValueType() {
		return valueType;
	}
	
	//method
	@Override
	public final <E2 extends IEntity<IMPL>> boolean referencesTable(final ITable<IMPL, E2> table) {
		return false;
	}
}
