//package declaration
package ch.nolix.systemapi.objectdatabaseapi.schemamapperapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParametrizedPropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//interface
public interface IParametrizedPropertyTypeMapper {
	
	//method declaration
	IParametrizedPropertyType createParametrizedPropertyTypeFromGivenPropertyUsingGivenReferencableTables(
		IProperty property,
		IContainer<ITable> referencableTables
	);
}
