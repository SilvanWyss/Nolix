//package declaration
package ch.nolix.systemapi.objectdatabaseapi.schemamapperapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedPropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//interface
public interface IParameterizedPropertyTypeMapper {

  //method declaration
  IParameterizedPropertyType createParameterizedPropertyTypeFromGivenPropertyUsingGivenReferencableTables(
    IProperty property,
    IContainer<ITable> referencableTables);
}
