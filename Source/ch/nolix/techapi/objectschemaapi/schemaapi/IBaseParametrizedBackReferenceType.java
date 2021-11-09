//package declaration
package ch.nolix.techapi.objectschemaapi.schemaapi;

//interface
public interface IBaseParametrizedBackReferenceType<PPT extends IParametrizedPropertyType<PPT, String>>
extends IParametrizedPropertyType<PPT, String> {
	
	//method declaration
	IColumn<?, ?> getBackReferencedColumn();
}
