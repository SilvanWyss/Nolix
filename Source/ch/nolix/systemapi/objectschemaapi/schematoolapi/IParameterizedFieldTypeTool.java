package ch.nolix.systemapi.objectschemaapi.schematoolapi;

import ch.nolix.systemapi.databaseobjectapi.databaseobjecttoolapi.IDatabaseObjectTool;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedFieldType;

public interface IParameterizedFieldTypeTool extends IDatabaseObjectTool {

  boolean isABaseBackReferenceType(IParameterizedFieldType parameterizedFieldType);

  boolean isABaseReferenceType(IParameterizedFieldType parameterizedFieldType);

  boolean isABaseValueType(IParameterizedFieldType parameterizedFieldType);
}
