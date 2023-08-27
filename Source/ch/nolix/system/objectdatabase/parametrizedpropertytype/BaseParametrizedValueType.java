//package declaration
package ch.nolix.system.objectdatabase.parametrizedpropertytype;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseParametrizedBackReferenceType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseParametrizedReferenceType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IBaseParametrizedValueType;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;

//class
public abstract class BaseParametrizedValueType<

	V
>
extends ParameterizedPropertyType
implements IBaseParametrizedValueType<V> {
	
	//attribute
	private final Class<V> valueType;
	
	//constructor
	protected BaseParametrizedValueType(final Class<V> valueType) {
		
		GlobalValidator.assertThat(valueType).thatIsNamed(LowerCaseCatalogue.VALUE_TYPE).isNotNull();
		
		this.valueType = valueType;
	}
	
	//method
	@Override
	public final IBaseParametrizedBackReferenceType<?> asBaseParametrizedBackReferenceType() {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParametrizedBackReferenceType");
	}
	
	//method
	@Override
	public final IBaseParametrizedReferenceType<?> asBaseParametrizedReferenceType() {
		throw ArgumentDoesNotSupportMethodException.forArgumentAndMethodName(this, "asBaseParametrizedReferenceType");
	}
	
	//method
	@Override
	public final IBaseParametrizedValueType<?> asBaseParametrizedValueType() {
		return this;
	}
	
	//method
	@Override
	public final Class<V> getValueType() {
		return valueType;
	}
	
	//method
	@Override
	public final boolean referencesTable(final ITable<?> table) {
		return false;
	}
}
