package ch.nolix.systemapi.objectschemaapi.schematoolapi;

import ch.nolix.systemapi.databaseobjectapi.modelexaminerapi.IDatabaseObjectExaminer;
import ch.nolix.systemapi.objectschemaapi.modelapi.IContentModel;

public interface IParameterizedFieldTypeTool extends IDatabaseObjectExaminer {

  boolean isABaseBackReferenceType(IContentModel contentModel);

  boolean isABaseReferenceType(IContentModel contentModel);

  boolean isABaseValueType(IContentModel contentModel);
}
