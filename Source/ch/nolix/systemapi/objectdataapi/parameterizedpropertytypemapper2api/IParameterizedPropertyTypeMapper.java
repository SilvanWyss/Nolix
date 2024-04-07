//package declaration
package ch.nolix.systemapi.objectdataapi.parameterizedpropertytypemapper2api;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.IField;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedPropertyType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;

//interface
public interface IParameterizedPropertyTypeMapper<P extends IField> {

  //method declaration
  IParameterizedPropertyType createParameterizedPropertyTypeFromProperty(
    P property,
    IContainer<ITable> referencedTables);
}
