package ch.nolix.systemapi.objectschemaapi.schematoolapi;

import ch.nolix.systemapi.databaseobjectapi.databaseobjecttoolapi.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IContentModel;

public interface IParameterizedFieldTypeTool extends IDatabaseObjectExaminer {

  boolean isABaseBackReferenceType(IContentModel contentModel);

  boolean isABaseReferenceType(IContentModel contentModel);

  boolean isABaseValueType(IContentModel contentModel);
}
