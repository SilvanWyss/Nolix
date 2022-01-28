//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

//interface
public interface IBaseParametrizedBackReferenceType<IMPL> extends IParametrizedPropertyType<IMPL, String> {
	
	//method declaration
	IColumn<IMPL> getBackReferencedColumn();
}
