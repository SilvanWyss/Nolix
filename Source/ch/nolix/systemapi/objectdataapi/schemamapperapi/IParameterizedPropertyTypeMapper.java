//package declaration
package ch.nolix.systemapi.objectdataapi.schemamapperapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedFieldType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//interface
public interface IParameterizedPropertyTypeMapper {

  //method declaration
  IParameterizedFieldType createParameterizedPropertyTypeFromProperty(
    IField field,
    IContainer<ITable> referencedTables);
}
