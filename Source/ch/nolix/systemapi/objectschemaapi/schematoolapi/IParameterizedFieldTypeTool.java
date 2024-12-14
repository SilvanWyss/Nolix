package ch.nolix.systemapi.objectschemaapi.schematoolapi;

import ch.nolix.systemapi.databaseobjectapi.databaseobjecttoolapi.IDatabaseObjectTool;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IContentModel;

public interface IParameterizedFieldTypeTool extends IDatabaseObjectTool {

  boolean isABaseBackReferenceType(IContentModel contentModel);

  boolean isABaseReferenceType(IContentModel contentModel);

  boolean isABaseValueType(IContentModel contentModel);
}
