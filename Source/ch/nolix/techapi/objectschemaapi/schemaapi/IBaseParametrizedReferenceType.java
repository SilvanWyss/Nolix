//package declaration
package ch.nolix.techapi.objectschemaapi.schemaapi;

//interface
public interface IBaseParametrizedReferenceType<IMPL> extends IParametrizedPropertyType<IMPL, String> {
	
	//method declaration
	ITable<IMPL> getReferencedTable();
}
