//package declaration
package ch.nolix.systemapi.objectschemaapi.schematoolapi;

import ch.nolix.systemapi.databaseobjectapi.databaseobjecttoolapi.IDatabaseObjectTool;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedPropertyType;

//interface
public interface IParameterizedPropertyTypeTool extends IDatabaseObjectTool {

  //method declaration
  boolean isABaseBackReferenceType(IParameterizedPropertyType parameterizedPropertyType);

  //method declaration
  boolean isABaseReferenceType(IParameterizedPropertyType parameterizedPropertyType);

  //method declaration
  boolean isABaseValueType(IParameterizedPropertyType parameterizedPropertyType);
}
