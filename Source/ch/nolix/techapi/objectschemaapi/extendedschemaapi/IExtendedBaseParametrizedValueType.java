//package declaration
package ch.nolix.techapi.objectschemaapi.extendedschemaapi;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.techapi.objectschemaapi.schemaapi.IBaseParametrizedReferenceType;
import ch.nolix.techapi.objectschemaapi.schemaapi.IBaseParametrizedValueType;
import ch.nolix.techapi.objectschemaapi.schemaapi.IParametrizedPropertyType;

//interface
public interface IExtendedBaseParametrizedValueType<
	EPPT extends IParametrizedPropertyType<EPPT, V>,
	V
> extends IBaseParametrizedValueType<EPPT, V> {
	
	//method
	@Override
	default IBaseParametrizedReferenceType<?> asBaseParametrizedReferenceType() {
		throw new ArgumentDoesNotSupportMethodException(this, "asBaseParametrizedReferenceType");
	}
}
