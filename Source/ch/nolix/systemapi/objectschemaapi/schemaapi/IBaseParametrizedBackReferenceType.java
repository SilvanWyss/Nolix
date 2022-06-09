//package declaration
package ch.nolix.systemapi.objectschemaapi.schemaapi;

//interface
public interface IBaseParametrizedBackReferenceType<IMPL> extends IParametrizedPropertyType<IMPL> {
	
	//method declaration
	IColumn<IMPL> getBackReferencedColumn();
}
