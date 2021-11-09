//package declaration
package ch.nolix.techapi.objectschemaapi.extendedschemaapi;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.techapi.objectschemaapi.schemaapi.IBaseParametrizedBackReferenceType;
import ch.nolix.techapi.objectschemaapi.schemaapi.IBaseParametrizedReferenceType;

//interface
public interface IExtendedBaseParametrizedBackReferenceType<
	EPPT extends IExtendedParametrizedPropertyType<EPPT, String>
>
extends IBaseParametrizedBackReferenceType<EPPT> {
	
	//method
	@Override
	default IBaseParametrizedReferenceType<?> asBaseParametrizedReferenceType() {
		throw new ArgumentDoesNotSupportMethodException(this, "asBaseParametrizedReferenceType");
	}
}
