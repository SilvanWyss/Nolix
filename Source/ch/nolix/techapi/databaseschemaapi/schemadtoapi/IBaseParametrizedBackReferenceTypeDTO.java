//package declaration
package ch.nolix.techapi.databaseschemaapi.schemadtoapi;

//interface
public interface IBaseParametrizedBackReferenceTypeDTO extends IParametrizedPropertyTypeDTO {
	
	//method declaration
	String getBackReferencedColumnHeader();
	
	//method declaration
	String getBackReferencedTableName();
}
