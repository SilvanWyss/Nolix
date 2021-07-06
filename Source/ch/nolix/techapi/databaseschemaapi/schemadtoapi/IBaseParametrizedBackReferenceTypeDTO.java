//package declaration
package ch.nolix.techapi.databaseschemaapi.schemadtoapi;

//interface
public interface IBaseParametrizedBackReferenceTypeDTO extends IBaseParametrizedPropertyTypeDTO {
	
	//method declaration
	String getBackReferencedColumnHeader();
	
	//method declaration
	String getBackReferencedTableName();
}
