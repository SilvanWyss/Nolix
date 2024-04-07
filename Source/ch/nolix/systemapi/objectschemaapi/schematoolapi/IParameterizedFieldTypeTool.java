//package declaration
package ch.nolix.systemapi.objectschemaapi.schematoolapi;

//own imports
import ch.nolix.systemapi.databaseobjectapi.databaseobjecttoolapi.IDatabaseObjectTool;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedFieldType;

//interface
public interface IParameterizedFieldTypeTool extends IDatabaseObjectTool {

  //method declaration
  boolean isABaseBackReferenceType(IParameterizedFieldType parameterizedFieldType);

  //method declaration
  boolean isABaseReferenceType(IParameterizedFieldType parameterizedFieldType);

  //method declaration
  boolean isABaseValueType(IParameterizedFieldType parameterizedFieldType);
}
