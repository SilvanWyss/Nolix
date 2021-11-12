//package declaration
package ch.nolix.techapi.objectschemaapi.extendedschemaapi;

//own imports
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotSupportMethodException;
import ch.nolix.techapi.objectschemaapi.schemaapi.IBaseParametrizedReferenceType;

//interface
public interface IExtendedBaseParametrizedReferenceType<EPPT extends IExtendedParametrizedPropertyType<EPPT, String>>
extends IBaseParametrizedReferenceType<EPPT>, IExtendedParametrizedPropertyType<EPPT, String> {
	
	//method
	@Override
	default IExtendedBaseParametrizedBackReferenceType<?> asBaseParametrizedBackReferenceType() {
		throw new ArgumentDoesNotSupportMethodException(this, "asBaseParametrizedBackReferenceType");
	}
	
	//method
	@Override
	default IExtendedBaseParametrizedReferenceType<?> asBaseParametrizedReferenceType() {
		return this;
	}
	
	//method
	@Override
	default IExtendedBaseParametrizedValueType<?, ?> asBaseParametrizedValueType() {
		throw new ArgumentDoesNotSupportMethodException(this, "asBaseParametrizedValueType");
	}
	
	//method declaration
	IExtendedTable<?, ?, ?> getReferencedTable();
}
