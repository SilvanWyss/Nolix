//package declaration
package ch.nolix.techapi.objectschemaapi.schemaapi;

//interface
public interface IBaseParametrizedBackReferenceType extends IParametrizedPropertyType<String> {
	
	//method declaration
	IColumn getBackReferencedColumn();
}
