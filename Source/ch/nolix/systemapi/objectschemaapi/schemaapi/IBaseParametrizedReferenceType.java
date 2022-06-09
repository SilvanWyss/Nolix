//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

//interface
public interface IBaseParametrizedReferenceType<IMPL> extends IParametrizedPropertyType<IMPL> {
	
	//method declaration
	ITable<IMPL> getReferencedTable();
}
