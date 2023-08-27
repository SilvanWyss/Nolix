//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

//interface
public interface IBaseParametrizedBackReferenceType extends IParameterizedPropertyType {
	
	//method declaration
	IColumn getBackReferencedColumn();
}
