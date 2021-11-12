//package declaration
package ch.nolix.techapi.objectschemaapi.extendedschemaapi;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.techapi.objectschemaapi.schemaapi.IBaseParametrizedValueType;
import ch.nolix.techapi.objectschemaapi.schemaapi.IParametrizedPropertyType;

//interface
public interface IExtendedBaseParametrizedValueType<
	EPPT extends IParametrizedPropertyType<EPPT, V>,
	V
> extends IBaseParametrizedValueType<EPPT, V> {
	
	//method
	@Override
	default IExtendedBaseParametrizedBackReferenceType<?> asBaseParametrizedBackReferenceType() {
		throw new ArgumentDoesNotSupportMethodException(this, "asBaseParametrizedBackReferenceType");
	}
	
	//method
	@Override
	default IExtendedBaseParametrizedReferenceType<?> asBaseParametrizedReferenceType() {
		throw new ArgumentDoesNotSupportMethodException(this, "asBaseParametrizedReferenceType");
	}
	
	//method
	@Override
	default IExtendedBaseParametrizedValueType<?, ?> asBaseParametrizedValueType() {
		return this;
	}
}
