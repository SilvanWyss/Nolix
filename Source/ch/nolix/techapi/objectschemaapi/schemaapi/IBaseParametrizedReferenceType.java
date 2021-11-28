//package declaration
package ch.nolix.techapi.objectschemaapi.schemaapi;

//interface
public interface IBaseParametrizedReferenceType extends IParametrizedPropertyType<String> {
	
	//method declaration
	ITable getReferencedTable();
}
