//package declaration
package ch.nolix.systemapi.objectdatabaseapi.parameterizedpropertytypemapper2api;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedPropertyType;

//interface
public interface IParameterizedPropertyTypeMapper<P extends IProperty> {

  //method declaration
  IParameterizedPropertyType createParameterizedPropertyTypeFromProperty(
    P property,
    IContainer<? extends ITable<IEntity>> referencedTables);
}
