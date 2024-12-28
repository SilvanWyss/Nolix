package ch.nolix.system.objectschema.schematool;

import ch.nolix.system.databaseobject.modelexaminer.DatabaseObjectExaminer;
import ch.nolix.systemapi.objectdataapi.fieldproperty.BaseContentType;
import ch.nolix.systemapi.objectschemaapi.modelapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.schematoolapi.IParameterizedFieldTypeTool;

public final class ParameterizedFieldTypeTool extends DatabaseObjectExaminer
implements IParameterizedFieldTypeTool {

  @Override
  public boolean isABaseBackReferenceType(final IContentModel contentModel) {
    return (contentModel.getContentType().getBaseType() == BaseContentType.BASE_BACK_REFERENCE);
  }

  @Override
  public boolean isABaseReferenceType(IContentModel contentModel) {
    return (contentModel.getContentType().getBaseType() == BaseContentType.BASE_REFERENCE);
  }

  @Override
  public boolean isABaseValueType(IContentModel contentModel) {
    return (contentModel.getContentType().getBaseType() == BaseContentType.BASE_VALUE);
  }
}
