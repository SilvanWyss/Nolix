//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

//interface
public interface IBaseParametrizedBackReferenceType extends IParametrizedPropertyType {
	
	//method declaration
	IColumn getBackReferencedColumn();
}
