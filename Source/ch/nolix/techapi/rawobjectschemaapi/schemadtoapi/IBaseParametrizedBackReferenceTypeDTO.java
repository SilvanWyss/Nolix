//package declaration
package ch.nolix.techapi.rawobjectschemaapi.schemadtoapi;

//interface
public interface IBaseParametrizedBackReferenceTypeDTO extends IParametrizedPropertyTypeDTO {
	
	//method declaration
	String getBackReferencedColumnName();
	
	//method declaration
	String getBackReferencedTableName();
}
