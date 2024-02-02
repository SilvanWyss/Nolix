//package declaration
package ch.nolix.systemapi.objectdataapi.dataapi;

//own imports
import ch.nolix.systemapi.entitypropertyapi.mainapi.PropertyType;

//interface
public interface IParameterizedPropertyType {

  //method declaration
  IBaseParameterizedBackReferenceType<?> asBaseParameterizedBackReferenceType();

  //method declaration
  IBaseParameterizedReferenceType<?> asBaseParameterizedReferenceType();

  //method declaration
  IBaseParameterizedValueType<?> asBaseParameterizedValueType();

  //method declaration
  PropertyType getPropertyType();

  //method declaration
  boolean referencesTable(ITable<?> table);
}
