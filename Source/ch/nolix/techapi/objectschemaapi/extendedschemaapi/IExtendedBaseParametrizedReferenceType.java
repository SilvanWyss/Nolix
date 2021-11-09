//package declaration
package ch.nolix.techapi.objectschemaapi.extendedschemaapi;

//own imports
import ch.nolix.techapi.objectschemaapi.schemaapi.IBaseParametrizedReferenceType;

//interface
public interface IExtendedBaseParametrizedReferenceType<EPPT extends IExtendedParametrizedPropertyType<EPPT, String>>
extends IBaseParametrizedReferenceType<EPPT>, IExtendedParametrizedPropertyType<EPPT, String> {
	
	//method
	@Override
	default IExtendedBaseParametrizedReferenceType<?> asBaseParametrizedReferenceType() {
		return this;
	}
	
	//method declaration
	IExtendedTable<?, ?, ?> getReferencedTable();
}
