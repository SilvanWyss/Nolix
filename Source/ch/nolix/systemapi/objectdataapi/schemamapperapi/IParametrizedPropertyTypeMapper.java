//package declaration
package ch.nolix.systemapi.objectdataapi.schemamapperapi;

//own imports
import ch.nolix.core.container.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;
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
