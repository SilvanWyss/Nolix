//package declaration
package ch.nolix.systemapi.objectdatabaseapi.databaseapi;

//own imports
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

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
