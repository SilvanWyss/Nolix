//package declaration
package ch.nolix.techapi.objectschemaapi.extendedschemaapi;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.techapi.objectschemaapi.schemaapi.IBaseParametrizedBackReferenceType;

//interface
public interface IExtendedBaseParametrizedBackReferenceType<
	EPPT extends IExtendedParametrizedPropertyType<EPPT, String>
>
extends IBaseParametrizedBackReferenceType<EPPT> {
	
	//method
	@Override
	default IExtendedBaseParametrizedBackReferenceType<?> asBaseParametrizedBackReferenceType() {
		return this;
	}
	
	//method
	@Override
	default IExtendedBaseParametrizedReferenceType<?> asBaseParametrizedReferenceType() {
		throw new ArgumentDoesNotSupportMethodException(this, "asBaseParametrizedReferenceType");
	}
	
	//method
	@Override
	default IExtendedBaseParametrizedValueType<?, ?> asBaseParametrizedValueType() {
		throw new ArgumentDoesNotSupportMethodException(this, "asBaseParametrizedValueType");
	}
}
