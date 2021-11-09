//package declaration
package ch.nolix.techapi.objectschemaapi.schemaapi;

//interface
public interface IBaseParametrizedReferenceType<PPT extends IParametrizedPropertyType<PPT, String>>
extends IParametrizedPropertyType<PPT, String> {
	
	//method declaration
	ITable<?, ?, ?> getReferencedTable();
}
