//package declaration
package ch.nolix.systemapi.objectdatabaseapi.schemamapperapi;

//own imports
import ch.nolix.coreapi.containerapi.mainapi.IContainer;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParametrizedPropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//interface
public interface IParametrizedPropertyTypeMapper<IMPL> {
	
	//method declaration
	IParametrizedPropertyType<IMPL> createParametrizedPropertyTypeFromGivenPropertyUsingGivenReferencableTables(
		IProperty<?> property,
		IContainer<ITable<IMPL>> referencableTables
	);
}
